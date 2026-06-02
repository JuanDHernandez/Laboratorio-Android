package com.example.mindkeep

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_dashboard)

        // Enlazamos el botón flotante por su ID definido en el XML
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAddNote)

        // Acción: Navegar hacia la actividad del formulario
        fabAdd.setOnClickListener {
            val intent = Intent(this, NoteFormActivity::class.java)
            startActivity(intent)
        }
    }
}