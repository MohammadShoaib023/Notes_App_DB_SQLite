package com.example.notessqlite

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes:List<Note>,context: Context):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
private val db:NoteDataClassHelper= NoteDataClassHelper(context)
    class NotesViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val titleTextView:TextView=itemView.findViewById(R.id.titleTextView)
        val contentTitleView:TextView=itemView.findViewById(R.id.contentTextView)
      val updateButton:ImageView=itemView.findViewById(R.id.updateButton)
       val deleteButton:ImageView=itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int =notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.titleTextView.text=note.title
        holder.contentTitleView.text=note.content
        holder.updateButton.setOnClickListener {
            val intent=Intent(holder.itemView.context,UpdateNoteActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
                holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener {
            db.deleteNoteById(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note Deleted successfully",Toast.LENGTH_SHORT).show()
        }
    }
    fun refreshData(newNotes:List<Note>){
        notes=newNotes
        notifyDataSetChanged()
    }

}