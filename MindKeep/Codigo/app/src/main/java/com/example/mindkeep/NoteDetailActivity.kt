package com.example.mindkeep

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        dbHelper = DatabaseHelper(this)

        val txtTitle = findViewById<TextView>(R.id.txtDetailTitle)
        val txtDate = findViewById<TextView>(R.id.txtDetailDate)
        val txtContent = findViewById<TextView>(R.id.txtDetailContent)
        val btnBack = findViewById<TextView>(R.id.btnBack)

        val noteId = intent.getIntExtra("NOTE_ID", -1)

        if (noteId != -1) {
            val db = dbHelper.readableDatabase
            val cursor = db.query(
                DatabaseHelper.TABLE_NOTES,
                null,
                "${DatabaseHelper.COL_NOTE_ID} = ?",
                arrayOf(noteId.toString()),
                null, null, null
            )

            if (cursor.moveToFirst()) {
                txtTitle.text = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_TITLE))
                txtDate.text = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_DATE))
                txtContent.text = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_CONTENT))
            }
            cursor.close()
        }

        btnBack.setOnClickListener { finish() }
    }
}