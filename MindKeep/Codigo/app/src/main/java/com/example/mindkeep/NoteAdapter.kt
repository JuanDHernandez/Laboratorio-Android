package com.example.mindkeep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val noteList: List<Note>, private val listener: (Note) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.txtItemTitle)
        val date: TextView = view.findViewById(R.id.txtItemDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.title.text = note.title
        holder.date.text = note.date
        holder.itemView.setOnClickListener { listener(note) }
    }

    override fun getItemCount() = noteList.size
}

// Clase de datos prolija
data class Note(val id: Int, val title: String, val content: String, val date: String)