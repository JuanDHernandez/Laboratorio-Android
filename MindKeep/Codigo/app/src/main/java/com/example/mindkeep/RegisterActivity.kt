package com.example.mindkeep

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 1. Buscamos los elementos del diseño usando los IDs del XML
        val edtNombres = findViewById<EditText>(R.id.editTextFirstName)
        val edtApellidos = findViewById<EditText>(R.id.editTextLastName)
        val edtEmail = findViewById<EditText>(R.id.editTextRegisterEmail)
        val edtPassword = findViewById<EditText>(R.id.editTextRegisterPassword)
        val edtConfirm = findViewById<EditText>(R.id.editTextConfirmPassword)
        val btnGuardar = findViewById<Button>(R.id.buttonSaveUser)
        val textBack = findViewById<TextView>(R.id.textBackToLogin)

        // 2. Definimos qué pasa al presionar "Guardar"
        btnGuardar.setOnClickListener {
            // Capturamos el texto actual de los campos
            val nombres = edtNombres.text.toString()
            val email = edtEmail.text.toString()
            val pass = edtPassword.text.toString()
            val confirm = edtConfirm.text.toString()

            // 3. Validación simple
            if (nombres.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (pass != confirm) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí es donde en el futuro guardaremos en la BD
                Toast.makeText(this, "Usuario registrado: $nombres", Toast.LENGTH_LONG).show()
                finish() // Regresamos al Login
            }
        }

        // 4. Botón para volver atrás
        textBack.setOnClickListener {
            finish()
        }
    }
}