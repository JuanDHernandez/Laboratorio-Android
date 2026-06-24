package com.example.mindkeep

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private var verifiedUserId: Int = -1
    private var isUserVerified: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        dbHelper = DatabaseHelper(this)

        val edtEmail = findViewById<EditText>(R.id.editTextForgotEmail)
        val btnSend = findViewById<Button>(R.id.buttonSendEmail)
        val textBack = findViewById<TextView>(R.id.textBackToLoginFromForgot)

        btnSend.setOnClickListener {
            val inputData = edtEmail.text.toString().trim()

            if (inputData.isEmpty()) {
                val mensaje = if (!isUserVerified) "Por favor, ingresa tu correo registrado" else "Por favor, ingresa tu nueva contraseña"
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isUserVerified) {
                // FASE 1: VALIDACIÓN REAL EN SQLITE
                val userId = dbHelper.getUserId(inputData)

                if (userId != -1) {
                    verifiedUserId = userId
                    isUserVerified = true

                    // Mutación de la interfaz gráfica en tiempo de ejecución
                    edtEmail.text.clear()
                    edtEmail.hint = "Ingresa tu nueva contraseña"
                    edtEmail.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

                    btnSend.text = "Actualizar Contraseña"

                    Toast.makeText(this, "Usuario verificado. Digita la nueva clave.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "El correo ingresado no se encuentra registrado", Toast.LENGTH_LONG).show()
                }
            } else {
                // FASE 2: ACTUALIZACIÓN DIRECTA EN SQLITE
                val exito = dbHelper.updatePassword(verifiedUserId, inputData)

                if (exito) {
                    Toast.makeText(this, "Contraseña modificada con éxito", Toast.LENGTH_LONG).show()
                    finish() // Retorna de forma segura al control de acceso primario
                } else {
                    Toast.makeText(this, "Error de consistencia al actualizar en el dispositivo", Toast.LENGTH_SHORT).show()
                }
            }
        }

        textBack.setOnClickListener {
            finish()
        }
    }
}