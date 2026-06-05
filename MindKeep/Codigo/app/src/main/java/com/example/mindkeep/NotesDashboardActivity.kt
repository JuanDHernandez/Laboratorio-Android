package com.example.mindkeep

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.jvm.java

class NotesDashboardActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_dashboard)

        dbHelper = DatabaseHelper(this)

        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAddNote)
        val textSalir = findViewById<TextView>(R.id.lblSalir)
        recyclerView = findViewById(R.id.recyclerViewNotes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fabAdd.setOnClickListener {
            val intent = Intent(this, NoteFormActivity::class.java)
            startActivity(intent)
        }

        textSalir.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        loadNotes()
    }

    override fun onResume() {
        super.onResume()
        loadNotes()
    }

    private fun loadNotes() {
        val cursor = dbHelper.getUserNotes(1)
        val noteList = mutableListOf<Note>()

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_TITLE))
                val content = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_CONTENT))
                val date = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_DATE))
                noteList.add(Note(id, title, content, date))
            } while (cursor.moveToNext())
        }
        cursor.close()

        val adapter = NoteAdapter(noteList) { note ->
            // Aquí es donde llamamos a la otra pantalla
            val intent = Intent(this, NoteDetailActivity::class.java)
            intent.putExtra("NOTE_ID", note.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}