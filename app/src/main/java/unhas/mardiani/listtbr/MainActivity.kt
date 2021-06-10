package unhas.mardiani.listtbr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import unhas.mardiani.listtbr.room.Note
import unhas.mardiani.listtbr.room.NoteDB

class MainActivity : AppCompatActivity() {

    val db by lazy { NoteDB(this) }
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNotes()
            Log.d("MainActivity", "dbResponse: $notes")
            withContext(Dispatchers.Main) {
                noteAdapter.setData( notes )
            }
        }
    }

    fun setupListener() {
        button_create.setOnClickListener {
            startActivity(Intent(this, EditActivity::class.java))
        }
    }

    fun setupRecyclerView() {
        noteAdapter = NoteAdapter(arrayListOf())
        list_note.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = noteAdapter
        }
    }
}