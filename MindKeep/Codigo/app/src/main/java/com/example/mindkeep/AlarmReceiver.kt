package com.example.mindkeep

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val noteTitle = intent.getStringExtra("NOTE_TITLE") ?: "Recordatorio de Nota"
        val noteContent = intent.getStringExtra("NOTE_CONTENT") ?: "Tienes una tarea pendiente en MindKeep"

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "mindkeep_reminders"

        // Corrección técnica: Se cambió la coma por el punto antes de la O
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Recordatorios MindKeep",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Canal destinado a las alertas y alarmas programadas de las notas"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val mainIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(noteTitle)
            .setContentText(noteContent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }
}