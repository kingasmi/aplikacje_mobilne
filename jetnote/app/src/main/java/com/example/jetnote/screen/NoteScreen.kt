package com.example.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.componets.NoteButton
import com.example.jetnote.componets.NoteInputText
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note
import java.time.format.DateTimeFormatter

@Composable
fun NoteScreen (
    notes: List<Note>,
    onAddNotes: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
){

    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context= LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text =  "jetnote")},
            actions = {
            Icon(
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Icon"
            )
        }, backgroundColor = Color(0xFF4CAF50))
        //CONTENT
        Column (
            modifier= Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            NoteInputText(
                Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() })
                        title = it
                })
            NoteInputText(
                Modifier.padding(top = 9.dp, bottom = 8.dp),
                text = description,
                label = "Add a note",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() })
                        description = it
                })
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()){
                    onAddNotes(Note(title =title, description=description))
                    title = ""
                    description=""
                    Toast.makeText(context,"Note Added", Toast.LENGTH_SHORT).show()
                }
            })
        }
        Divider(Modifier.padding(30.dp))
        LazyColumn{
            items(notes){ note ->
                NoteRow(note = note, onNoteClicked = {
                    onRemoveNote(note) })
            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomEnd = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFF03A9F4),
        elevation = 6.dp
    ) {
        Column(
            modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = note.title, style = MaterialTheme.typography.subtitle2)
            Text(text = note.description, style = MaterialTheme.typography.subtitle1)
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM")),
                style = MaterialTheme.typography.caption
            )
        }
    }
}


@Preview(showBackground = true,
    device = "spec:width=1080px,height=2340px,dpi=440"
)@Composable
fun NoteScreenPreview(){
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNotes = {}, onRemoveNote = {})
}