package com.example.notes.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.components.ColorButton
import com.example.notes.components.NoteButton
import com.example.notes.components.NoteInputText
import com.example.notes.data.NotesDataSource
import com.example.notes.model.Note
import com.example.notes.util.formatDate


@OptIn(ExperimentalMaterial3Api::class)
@androidx.compose.runtime.Composable
fun NotesScreen(
    notes: List<Note>,
    onAddNote: (Note)-> Unit,
    onRemoveNote: (Note) -> Unit,
)
{
    var color: Color by remember {
        mutableStateOf(Color(0xFF00B8D4))
    }
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Surface(
        modifier = Modifier,
        color = Color(0xFFE0E0E0)

    ){
        Column(modifier = Modifier.padding(5.dp)) {
            TopAppBar(
                modifier = Modifier.border(border = BorderStroke(1.dp, color = Color(0xFF000000))),

                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "Icon",
                        tint = Color(0xA1000000)
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF757575),
                    titleContentColor = Color(0xFF000000)
                )
            )
           /* Divider(modifier=Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Color(0xFF000000)
            )*/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(border = BorderStroke(1.dp, color = Color(0xFF000000))),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteInputText(
                    modifier = Modifier.padding(
                        top = 9.dp, bottom = 8.dp
                    ),
                    text = title,
                    label = "Title",
                    onTextChange =
                    {
                        if (it.all { char ->
                                char.isLetter() || char.isWhitespace()
                            }
                            ) title = it
                    }

                )


                NoteInputText(
                    modifier = Modifier.padding(
                        top = 9.dp, bottom = 8.dp
                    ),
                    text = description,
                    label = "Add a Note",
                    maxLine = 10,
                    onTextChange = {
                        if (it.all { char ->
                                char.isLetter() || char.isWhitespace()
                            }) description = it
                    }
                )

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ){

                    ColorButton(modifier = Modifier.padding(horizontal = 10.dp),
                        color = IconButtonDefaults.iconButtonColors(Color(0xFF00B8D4)),
                        onClick = { color = Color(0xFF00B8D4) })
                    ColorButton( color = IconButtonDefaults.iconButtonColors(Color(0xFFFF8F00))
                        , onClick = { color= Color(0xFFED8400) })
                    ColorButton( color = IconButtonDefaults.iconButtonColors(Color(0xFFFFFF8D))
                        , onClick = {
                            color=Color(0xFFFFFF8D)
                        })
                }


       //         Spacer(modifier = Modifier.height(20.dp))

                NoteButton(text = "Save", onClick = {
                    if(title.isNotEmpty() && description.isNotEmpty()){
                      //save to the list
                        onAddNote(Note(title = title, description = description))
                        title=""
                        description=""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                })
            }
            Divider(modifier = Modifier.padding(10.dp))

            LazyColumn(modifier=Modifier){

                items(notes){note ->
                    NoteRow(note = note,
                        initialColor =  color
                        , onNoteClicked = {
                        onRemoveNote(note)
                    })
                }
            }

        }
    }
    //content


}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    initialColor: Color= Color(0xFF00B8D4),
    onNoteClicked: (Note) -> Unit
) {

    val color: Color by remember {
        mutableStateOf(initialColor)
    }

    Surface(
        modifier = Modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth(),
        color = color,
        shadowElevation = 6.dp
    ) {
        Column(
            modifier
                // .clickable { onNoteClicked(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {

            Row(modifier= Modifier
                .fillMaxWidth()
                .height(45.dp)) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                    IconButton(
                        onClick = { onNoteClicked(note) },
                        modifier = Modifier
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete",
                            tint = Color.Black,
                            modifier = Modifier
                        )
                    }
                }
            }
            Text(text = note.description, style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier=Modifier.fillMaxWidth()
                ,text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.End)
        }

    }

}


@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun NotesScreenPreview() {
    NotesScreen(
        notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {}
    )
}