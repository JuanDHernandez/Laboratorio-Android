package com.example.mindkeep

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // 1. "Presentamos" los componentes
        val txtUsuario = findViewById<EditText>(R.id.editTextText)
        val btnIngresar = findViewById<Button>(R.id.button)

        // 2. Un solo bloque de acción para el botón
        btnIngresar.setOnClickListener {
            val nombre = txtUsuario.text.toString()

            if (nombre.isEmpty()) {
                Toast.makeText(this, "Por favor, escribe tu nombre", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí unificamos ambos mensajes
                Toast.makeText(this, "¡Conexión Exitosa! Hola $nombre", Toast.LENGTH_LONG).show()
            }
        }

    }
}