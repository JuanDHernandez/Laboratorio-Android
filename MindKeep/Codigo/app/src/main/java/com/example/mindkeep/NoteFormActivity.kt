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
    private var currentUserId: Int = -1
    private var noteId: Int = -1 // Almacena el ID si venimos en Modo Edición

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_form)

        dbHelper = DatabaseHelper(this)

        // Recuperar parámetros del Intent (ID de usuario o ID de la nota a editar)
        currentUserId = intent.getIntExtra("USER_ID", -1)
        noteId = intent.getIntExtra("NOTE_ID", -1)

        // 1. Vinculamos los campos usando los IDs EXACTOS de tu XML
        val edtTitulo = findViewById<EditText>(R.id.editNoteTitle)
        val edtContenido = findViewById<EditText>(R.id.editNoteContent)
        val btnSave = findViewById<Button>(R.id.buttonSaveNote)

        // Si el noteId es diferente de -1, significa que venimos desde el detalle en Modo Edición
        if (noteId != -1) {
            val titleExtra = intent.getStringExtra("NOTE_TITLE") ?: ""
            val contentExtra = intent.getStringExtra("NOTE_CONTENT") ?: ""

            // Precargamos la información existente en los inputs de la interfaz gráfica
            edtTitulo.setText(titleExtra)
            edtContenido.setText(contentExtra)

            // Opcional: Cambiar el texto del botón para denotar la acción de actualizar
            btnSave.text = "Actualizar Nota"
        }

        // 2. Acción para el botón de guardar / actualizar
        btnSave.setOnClickListener {
            val titulo = edtTitulo.text.toString().trim()
            val contenido = edtContenido.text.toString().trim()

            if (titulo.isEmpty() || contenido.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos de la nota", Toast.LENGTH_SHORT).show()
            } else {
                if (noteId != -1) {
                    // ---- MODO EDICIÓN: Ejecuta el UPDATE en SQLite ----
                    val exito = dbHelper.updateNote(noteId, titulo, contenido)
                    if (exito) {
                        Toast.makeText(this, "Nota actualizada con éxito", Toast.LENGTH_SHORT).show()
                        finish() // Retorna de inmediato al Dashboard
                    } else {
                        Toast.makeText(this, "Error al intentar actualizar la nota", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // ---- MODO CREACIÓN: Ejecuta el INSERT en SQLite ----
                    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                    val fechaActual = sdf.format(Date())

                    if (currentUserId != -1) {
                        val result = dbHelper.insertNote(currentUserId, titulo, contenido, fechaActual)
                        if (result > -1) {
                            Toast.makeText(this, "Nota guardada con éxito en SQLite", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this, "Error local al intentar guardar la nota", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Error de sesión: Usuario no identificado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}