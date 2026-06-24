package com.example.mindkeep

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MindKeep.db"
        private const val DATABASE_VERSION = 1

        // Estructura limpia y acoplada a tus pantallas de Login y Registro
        const val TABLE_USERS = "usuarios"
        const val COL_USER_ID = "id_usuario"
        const val COL_EMAIL = "email"
        const val COL_PASSWORD = "password"

        const val TABLE_NOTES = "notas"
        const val COL_NOTE_ID = "id_nota"
        const val COL_NOTE_USER_ID = "id_usuario_nota"
        const val COL_NOTE_TITLE = "titulo"
        const val COL_NOTE_CONTENT = "contenido"
        const val COL_NOTE_DATE = "fecha"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTable = ("CREATE TABLE " + TABLE_USERS + "("
                + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_EMAIL + " TEXT,"
                + COL_PASSWORD + " TEXT" + ")")
        db.execSQL(createUsersTable)

        val createNotesTable = ("CREATE TABLE " + TABLE_NOTES + "("
                + COL_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NOTE_USER_ID + " INTEGER,"
                + COL_NOTE_TITLE + " TEXT,"
                + COL_NOTE_CONTENT + " TEXT,"
                + COL_NOTE_DATE + " TEXT,"
                + "FOREIGN KEY(" + COL_NOTE_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COL_USER_ID + "))")
        db.execSQL(createNotesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES)
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS)
        onCreate(db)
    }

    // --- MÉTODOS DE USUARIOS ---

    fun insertUser(username: String, email: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_EMAIL, username)
            put(COL_PASSWORD, email)
        }
        return db.insert(TABLE_USERS, null, values)
    }

    fun checkUser(username: String, passwordString: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COL_USER_ID),
            "$COL_EMAIL = ? AND $COL_PASSWORD = ?",
            arrayOf(username, passwordString),
            null, null, null
        )
        val exists = cursor.count > 0
        cursor.close()
        return exists
    }

    fun getUserId(usernameOrEmail: String): Int {
        val db = this.readableDatabase
        var userId = -1
        val cursor = db.query(
            TABLE_USERS,
            arrayOf(COL_USER_ID),
            "$COL_EMAIL = ?",
            arrayOf(usernameOrEmail),
            null, null, null
        )

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndexOrThrow(COL_USER_ID))
        }
        cursor.close()
        return userId
    }

    fun updatePassword(userId: Int, newPasswordString: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COL_PASSWORD, newPasswordString)
        }
        val result = db.update(TABLE_USERS, contentValues, "$COL_USER_ID = ?", arrayOf(userId.toString()))
        return result > 0
    }

    // --- MÉTODOS DE NOTAS ---

    fun insertNote(userId: Int, title: String, content: String, date: String): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_NOTE_USER_ID, userId)
            put(COL_NOTE_TITLE, title)
            put(COL_NOTE_CONTENT, content)
            put(COL_NOTE_DATE, date)
        }
        return db.insert(TABLE_NOTES, null, values)
    }

    fun getUserNotes(userId: Int): Cursor {
        val db = this.readableDatabase
        return db.query(
            TABLE_NOTES,
            null,
            "$COL_NOTE_USER_ID = ?",
            arrayOf(userId.toString()),
            null, null, "$COL_NOTE_ID DESC"
        )
    }

    fun updateNote(noteId: Int, title: String, content: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_NOTE_TITLE, title)
            put(COL_NOTE_CONTENT, content)
        }
        val result = db.update(TABLE_NOTES, values, "$COL_NOTE_ID = ?", arrayOf(noteId.toString()))
        return result > 0
    }

    fun deleteNote(noteId: Int): Boolean {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NOTES, "$COL_NOTE_ID = ?", arrayOf(noteId.toString()))
        return result > 0
    }
}