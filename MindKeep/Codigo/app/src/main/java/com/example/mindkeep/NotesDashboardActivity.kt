package com.example.mindkeep

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NotesDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Le indicamos que use el diseño visual que verificaste hace un momento
        setContentView(R.layout.activity_notes_dashboard)

        // Enlazamos el botón flotante del "+" por su ID
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAddNote)

        // Acción temporal para cuando se presione el botón "+"
        fabAdd.setOnClickListener {
            Toast.makeText(this, "Función para crear nota próximamente", Toast.LENGTH_SHORT).show()
        }
    }
}