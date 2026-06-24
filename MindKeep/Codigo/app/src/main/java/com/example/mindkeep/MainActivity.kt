package com.example.mindkeep

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        val edtUsuario = findViewById<EditText>(R.id.editTextUser)
        val edtContrasena = findViewById<EditText>(R.id.editTextPassword)
        val btnIngresar = findViewById<Button>(R.id.buttonLogin)
        val textRegistrarse = findViewById<TextView>(R.id.textRegister)
        val textOlvidaste = findViewById<TextView>(R.id.textForgotPassword)

        btnIngresar.setOnClickListener {
            val usuario = edtUsuario.text.toString().trim()
            val contrasena = edtContrasena.text.toString().trim()

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val isValid = dbHelper.checkUser(usuario, contrasena)

                if (isValid) {
                    // Obtener el ID dinámico del usuario autenticado
                    val userId = dbHelper.getUserId(usuario)

                    Toast.makeText(this, "¡Conexión Exitosa! Hola $usuario", Toast.LENGTH_LONG).show()

                    // Pasamos al Dashboard inyectando el USER_ID real
                    val intent = Intent(this, NotesDashboardActivity::class.java)
                    intent.putExtra("USER_ID", userId)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
            }
        }

        textRegistrarse.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        textOlvidaste.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}