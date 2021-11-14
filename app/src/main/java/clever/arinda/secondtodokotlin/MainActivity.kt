package clever.arinda.secondtodokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickDeleteInterface,NoteClickInterface {

    lateinit var notesRv: RecyclerView
    lateinit var floatingBtn: FloatingActionButton
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notesRv = findViewById(R.id.rvNotes)
        floatingBtn = findViewById<FloatingActionButton>(R.id.floating_action_button)
        notesRv.layoutManager = LinearLayoutManager(this)

        val notesAdapter = NotesAdapter(this, this, this)
        notesRv.adapter = notesAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
            viewModel.allNotes.observe(this, Observer{ list->
                list?.let{
                    notesAdapter.updateList(it)
            }
        })

        floatingBtn.setOnClickListener{
           startActivity(Intent(this, AddEditNoteActivity::class.java))
            this.finish()
        }
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNotes(note)
        Toast.makeText(this,"Deleted : " + note.noteTitle, Toast.LENGTH_LONG).show()
        Toast.makeText(this,"${note.noteTitle} Deleted",Toast.LENGTH_LONG).show()
    }

    override fun onNoteClick(note: Note) {
        startActivity(Intent(this, AddEditNoteActivity::class.java))
        intent.putExtra("noteType","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.noteDescription)
        intent.putExtra("noteId",note.id)
        this.finish()

    }


}