package com.example.mindkeep

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        dbHelper = DatabaseHelper(this)

        val txtTitle = findViewById<TextView>(R.id.txtDetailTitle)
        val txtDate = findViewById<TextView>(R.id.txtDetailDate)
        val txtContent = findViewById<TextView>(R.id.txtDetailContent)
        val btnBack = findViewById<TextView>(R.id.btnBack)
        val btnDelete = findViewById<TextView>(R.id.btnDelete)
        val btnEdit = findViewById<TextView>(R.id.btnEdit)
        val btnAlarm = findViewById<TextView>(R.id.btnAlarm)

        val noteId = intent.getIntExtra("NOTE_ID", -1)

        var currentTitle = ""
        var currentContent = ""

        if (noteId != -1) {
            val db = dbHelper.readableDatabase
            val cursor = db.query(
                DatabaseHelper.TABLE_NOTES,
                null,
                "${DatabaseHelper.COL_NOTE_ID} = ?",
                arrayOf(noteId.toString()),
                null, null, null
            )

            if (cursor.moveToFirst()) {
                currentTitle = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_TITLE))
                currentContent = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_CONTENT))

                txtTitle.text = currentTitle
                txtDate.text = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_NOTE_DATE))
                txtContent.text = currentContent
            }
            cursor.close()
        }

        btnBack.setOnClickListener { finish() }

        btnEdit.setOnClickListener {
            if (noteId != -1) {
                val intent = Intent(this, NoteFormActivity::class.java)
                intent.putExtra("NOTE_ID", noteId)
                intent.putExtra("NOTE_TITLE", currentTitle)
                intent.putExtra("NOTE_CONTENT", currentContent)
                startActivity(intent)
                finish()
            }
        }

        btnDelete.setOnClickListener {
            if (noteId != -1) {
                val exito = dbHelper.deleteNote(noteId)
                if (exito) {
                    Toast.makeText(this, "Nota eliminada correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error al eliminar la nota", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnAlarm.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                val alarmCalendar = Calendar.getInstance().apply {
                    set(Calendar.HOUR_OF_DAY, selectedHour)
                    // Corrección técnica: Se cambió Calendar.SET por Calendar.MINUTE
                    set(Calendar.MINUTE, selectedMinute)
                    set(Calendar.SECOND, 0)
                    set(Calendar.MILLISECOND, 0)
                }

                if (alarmCalendar.timeInMillis <= System.currentTimeMillis()) {
                    alarmCalendar.add(Calendar.DAY_OF_YEAR, 1)
                }

                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intentReceiver = Intent(this, AlarmReceiver::class.java).apply {
                    putExtra("NOTE_TITLE", currentTitle)
                    putExtra("NOTE_CONTENT", currentContent)
                }

                val pendingIntent = PendingIntent.getBroadcast(
                    this,
                    noteId,
                    intentReceiver,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
                    Toast.makeText(this, "Por favor, concede el permiso de alarmas exactas", Toast.LENGTH_LONG).show()
                    val intentPermission = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                    startActivity(intentPermission)
                } else {
                    alarmManager.setExactAndAllowWhileIdle(
                        AlarmManager.RTC_WAKEUP,
                        alarmCalendar.timeInMillis,
                        pendingIntent
                    )
                    Toast.makeText(this, "Recordatorio agendado con éxito", Toast.LENGTH_SHORT).show()
                }

            }, hour, minute, true)

            timePickerDialog.show()
        }
    }
}