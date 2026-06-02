package com.example.mindkeep

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NoteFormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_form)

        // Enlazamos el botón de guardar
        val btnSave = findViewById<Button>(R.id.buttonSaveNote)

        btnSave.setOnClickListener {
            // Acción rápida de confirmación y cierre para la entrega
            Toast.makeText(this, "Nota guardada con éxito", Toast.LENGTH_SHORT).show()
            finish() // Esto cierra la actividad y regresa al Dashboard
        }
    }
}