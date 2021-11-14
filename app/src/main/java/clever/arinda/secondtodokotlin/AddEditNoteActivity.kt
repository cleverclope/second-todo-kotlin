package clever.arinda.secondtodokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ViewAnimator
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var noteEntered: EditText
    lateinit var noteDescription: EditText
    lateinit var addNoteBtn: Button
    lateinit var viewModel: NotesViewModel
    var noteId = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        noteEntered = findViewById(R.id.note_entered_id)
        noteDescription = findViewById(R.id.note_description_id)
        addNoteBtn = findViewById(R.id.btn_addNote)
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)

        val noteType = intent.getStringExtra("")
        if (noteType.equals("Edit")) {

            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            val noteId = intent.getIntExtra("", -1)

            addNoteBtn.setText("UpdateNote")
            noteEntered.setText(noteTitle)
            noteDescription.setText(noteDesc)
        } else {
            addNoteBtn.setText("SaveNote")
        }

        addNoteBtn.setOnClickListener {
            val noteTitle = noteEntered.text.toString()
            val noteDesc = noteDescription.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf = SimpleDateFormat("dd MMM - HH:mm")
                    val currentDate: String = sdf.format(Date())

                    val updateNote = Note(noteTitle,noteDesc,currentDate)
                    updateNote.id = noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG).show()
                }

            }
            else{
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()){
                    val sdf  = SimpleDateFormat("dd MMM - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle, noteDesc, currentDate))
                    Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()
                }
            }

            startActivity(Intent(this, MainActivity::class.java))
            this.finish()

        }

    }
}