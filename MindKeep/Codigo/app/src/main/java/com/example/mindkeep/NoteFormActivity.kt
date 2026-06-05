package com.example.mindkeep

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteFormActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_form)

        dbHelper = DatabaseHelper(this)

        // 1. Vinculamos los campos usando los IDs EXACTOS de tu XML
        val edtTitulo = findViewById<EditText>(R.id.editNoteTitle)
        val edtContenido = findViewById<EditText>(R.id.editNoteContent)
        val btnSave = findViewById<Button>(R.id.buttonSaveNote)

        // 2. Acción para el botón de guardar
        btnSave.setOnClickListener {
            val titulo = edtTitulo.text.toString().trim()
            val contenido = edtContenido.text.toString().trim()

            if (titulo.isEmpty() || contenido.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos de la nota", Toast.LENGTH_SHORT).show()
            } else {
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val fechaActual = sdf.format(Date())

                // Inserción real en SQLite usando el ID de usuario por defecto
                val result = dbHelper.insertNote(1, titulo, contenido, fechaActual)

                if (result > -1) {
                    Toast.makeText(this, "Nota guardada con éxito en SQLite", Toast.LENGTH_SHORT).show()
                    finish() // Cierra la actividad y regresa al Dashboard de forma automática
                } else {
                    Toast.makeText(this, "Error local al intentar guardar la nota", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}