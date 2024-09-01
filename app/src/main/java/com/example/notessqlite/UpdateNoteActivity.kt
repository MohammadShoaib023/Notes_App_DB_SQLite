package com.example.notessqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notessqlite.databinding.ActivityUpdateBinding

class UpdateNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db:NoteDataClassHelper
    private var noteId: Int=-1

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db=NoteDataClassHelper(this)
        noteId=intent.getIntExtra("note_id",-1)
        if (noteId==-1){
            finish()
            return
        }



        val note=db.getNoteByID(noteId)
        binding.updateTitleEditText.setText(note.title)
        binding.UpdateContentEditText.setText(note.content)



        binding.updateButton.setOnClickListener {
            val newTitle= binding.updateTitleEditText.text.toString()
            val newContent= binding.UpdateContentEditText.text.toString()
            val updateNote=Note(noteId,newTitle,newContent)
            db.updateNote(updateNote)
            finish()
            Toast.makeText(this,"Changes Saved",Toast.LENGTH_SHORT).show()

        }
    }
}