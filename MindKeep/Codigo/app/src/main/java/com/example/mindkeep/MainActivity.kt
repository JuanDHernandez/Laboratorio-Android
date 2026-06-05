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

    // Declaramos el ayudante de la base de datos
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicializamos el ayudante de la base de datos
        dbHelper = DatabaseHelper(this)

        // 1. Vinculamos los componentes del nuevo diseño usando sus IDs correctos
        val edtUsuario = findViewById<EditText>(R.id.editTextUser)
        val edtContrasena = findViewById<EditText>(R.id.editTextPassword)
        val btnIngresar = findViewById<Button>(R.id.buttonLogin)
        val textRegistrarse = findViewById<TextView>(R.id.textRegister)
        val textOlvidaste = findViewById<TextView>(R.id.textForgotPassword)

        // 2. Acción para el botón de Ingresar [ Login ]
        btnIngresar.setOnClickListener {
            val usuario = edtUsuario.text.toString().trim()
            val contrasena = edtContrasena.text.toString().trim()

            if (usuario.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Modificación Técnica: Validamos las credenciales contra SQLite real
                val isValid = dbHelper.checkUser(usuario, contrasena)

                if (isValid) {
                    Toast.makeText(this, "¡Conexión Exitosa! Hola $usuario", Toast.LENGTH_LONG).show()

                    // Saltamos al Dashboard de Notas si las credenciales son correctas
                    val intent = Intent(this, NotesDashboardActivity::class.java)
                    startActivity(intent)
                    finish() // Cierra el Login para que no regrese con el botón de atrás del teléfono
                } else {
                    // Si las credenciales no existen o están mal, bloqueamos el acceso
                    Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                }
            }
        }

        // 3. Al hacer clic en "Registrarse", abre la pantalla de Crear Cuenta
        textRegistrarse.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // 4. Al hacer clic en "¿Olvidaste tu contraseña?", abre la pantalla de recuperación
        textOlvidaste.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}