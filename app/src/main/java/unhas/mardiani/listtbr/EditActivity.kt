package unhas.mardiani.listtbr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import unhas.mardiani.listtbr.room.Note
import unhas.mardiani.listtbr.room.NoteDB

class EditActivity : AppCompatActivity() {

    val db by lazy { NoteDB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        setupListener()
    }

    fun setupListener() {
        button_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Note(0, edit_title.text.toString(), edit_writer.text.toString(), edit_note.text.toString())
                )
                finish()
            }
        }
    }
}