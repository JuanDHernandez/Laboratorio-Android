package com.example.mindkeep

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aquí le decimos que esta clase controle el diseño visual que creaste hace un momento
        setContentView(R.layout.activity_forgot_password)

        // Buscamos los componentes de la pantalla por sus IDs del XML
        val edtEmail = findViewById<EditText>(R.id.editTextForgotEmail)
        val btnSend = findViewById<Button>(R.id.buttonSendEmail)
        val textBack = findViewById<TextView>(R.id.textBackToLoginFromForgot)

        // Acción para cuando el usuario presione el botón "[ Enviar Enlace ]"
        btnSend.setOnClickListener {
            val email = edtEmail.text.toString()

            if (email.isEmpty()) {
                // Si el campo está vacío, le mostramos un aviso corto en pantalla
                Toast.makeText(this, "Por favor, ingresa tu correo", Toast.LENGTH_SHORT).show()
            } else {
                // Si escribió algo, simulamos el envío del enlace
                Toast.makeText(this, "Enlace enviado a: $email", Toast.LENGTH_LONG).show()
                finish() // Cierra esta pantalla automáticamente y lo regresa al Login
            }
        }

        // Acción para cuando haga clic en "¿Recordaste tu contraseña? Inicia sesión"
        textBack.setOnClickListener {
            finish() // Cierra esta pantalla y regresa de inmediato a la anterior
        }
    }
}