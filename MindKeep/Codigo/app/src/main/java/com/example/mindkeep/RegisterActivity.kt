package com.example.mindkeep

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Enlace para volver al Login si el usuario se arrepiente
        val textBackToLogin = findViewById<TextView>(R.id.textBackToLogin)
        textBackToLogin.setOnClickListener {
            finish() // Cierra esta pantalla y vuelve de inmediato a la anterior
        }
    }
}