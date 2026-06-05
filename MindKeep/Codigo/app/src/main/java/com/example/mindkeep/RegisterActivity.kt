package com.example.mindkeep

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    // Declaramos el ayudante de la base de datos
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializamos el ayudante de la base de datos
        dbHelper = DatabaseHelper(this)

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
            val nombres = edtNombres.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val pass = edtPassword.text.toString().trim()
            val confirm = edtConfirm.text.toString().trim()

            // 3. Validación simple
            if (nombres.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (pass != confirm) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            } else {
                // Modificación Técnica: Insertamos el usuario en la base de datos SQLite real
                // Usamos el 'email' como nombre de usuario único y la contraseña 'pass'
                val result = dbHelper.insertUser(email, pass)

                if (result > -1) {
                    Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_LONG).show()
                    finish() // Regresamos al Login de forma segura
                } else {
                    // Si el método devuelve -1 es porque el correo ya existe en la BD
                    Toast.makeText(this, "El correo electrónico ya está registrado", Toast.LENGTH_LONG).show()
                }
            }
        }

        // 4. Botón para volver atrás
        textBack.setOnClickListener {
            finish()
        }
    }
}