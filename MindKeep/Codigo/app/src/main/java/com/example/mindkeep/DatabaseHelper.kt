package com.example.mindkeep

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "MindKeep.db"
        private const val DATABASE_VERSION = 1

        // Tabla Usuarios
        const val TABLE_USERS = "usuarios"
        const val COL_USER_ID = "id"
        const val COL_USER_NAME = "username"
        const val COL_USER_PASSWORD = "password"

        // Tabla Notas
        const val TABLE_NOTES = "notas"
        const val COL_NOTE_ID = "id"
        const val COL_NOTE_USER_REF = "usuario_id"
        const val COL_NOTE_TITLE = "titulo"
        const val COL_NOTE_CONTENT = "contenido"
        const val COL_NOTE_DATE = "fecha_creacion"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUsersTable = ("CREATE TABLE " + TABLE_USERS + "("
                + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_USER_NAME + " TEXT UNIQUE,"
                + COL_USER_PASSWORD + " TEXT" + ")")

        val createNotesTable = ("CREATE TABLE " + TABLE_NOTES + "("
                + COL_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NOTE_USER_REF + " INTEGER,"
                + COL_NOTE_TITLE + " TEXT,"
                + COL_NOTE_CONTENT + " TEXT,"
                + COL_NOTE_DATE + " TEXT,"
                + "FOREIGN KEY(" + COL_NOTE_USER_REF + ") REFERENCES " + TABLE_USERS + "(" + COL_USER_ID + ")" + ")")

        db.execSQL(createUsersTable)
        db.execSQL(createNotesTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // Método para registrar un nuevo usuario en la base de datos
    fun insertUser(usernameInput: String, passwordInput: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_USER_NAME, usernameInput)
        values.put(COL_USER_PASSWORD, passwordInput)

        val result = db.insert(TABLE_USERS, null, values)
        db.close()
        return result
    }

    // Método para validar si las credenciales de inicio de sesión son correctas
    fun checkUser(usernameInput: String, passwordInput: String): Boolean {
        val db = this.readableDatabase
        val columns = arrayOf(COL_USER_ID)
        val selection = "$COL_USER_NAME = ? AND $COL_USER_PASSWORD = ?"
        val selectionArgs = arrayOf(usernameInput, passwordInput)

        val cursor = db.query(
            TABLE_USERS,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val count = cursor.count
        cursor.close()
        db.close()

        return count > 0
    }

    // Método para insertar una nueva nota asociada a un usuario específico
    fun insertNote(userId: Int, title: String, content: String, date: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NOTE_USER_REF, userId)
        values.put(COL_NOTE_TITLE, title)
        values.put(COL_NOTE_CONTENT, content)
        values.put(COL_NOTE_DATE, date)

        val result = db.insert(TABLE_NOTES, null, values)
        db.close()
        return result
    }

    // Método para obtener todas las notas de un usuario específico
    fun getUserNotes(userId: Int): android.database.Cursor {
        val db = this.readableDatabase
        val selection = "$COL_NOTE_USER_REF = ?"
        val selectionArgs = arrayOf(userId.toString())

        // Retorna el cursor con todas las filas encontradas ordenadas por ID descendente
        return db.query(
            TABLE_NOTES,
            null,
            selection,
            selectionArgs,
            null,
            null,
            "$COL_NOTE_ID DESC"
        )
    }
}