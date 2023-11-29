package com.example.notes.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.R
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
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    var iconShow by remember {
        mutableStateOf(true)
    }

    var noteShow by remember {
        mutableStateOf(true)
    }
    
    Surface(
        modifier = Modifier,
        color = Color(0xFFF5FFFE)

    ){
        Column(modifier = Modifier) {
/*            TopAppBar(
                modifier = Modifier,

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
            )*/

            Spacer(modifier = Modifier.height(37.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                enabled = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .border(border = BorderStroke(0.dp, color = Color.Transparent)),
                label = {
                    Text(text = "Search Your Notes")
                },
                leadingIcon = {
                    IconButton(onClick = {}, modifier = Modifier) {
                        Icon(imageVector = Icons.Outlined.List, contentDescription = "List")
                    }
                }, trailingIcon = {
                    Row {
                        if (iconShow) {
                            IconButton(onClick = {
                                iconShow = !iconShow
                                noteShow = !noteShow
                            }) {
                                Icon(
                                    painter = painterResource(R.drawable.grid),
                                    contentDescription = "grid",
                                    modifier = Modifier.size(30.dp)
                                )
                            }
                        } else {
                            IconButton(onClick = {
                                iconShow = !iconShow
                                noteShow = !noteShow
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icons8_row_24),
                                    contentDescription = "column",
                                    modifier = Modifier
                                        .size(35.dp)
                                        .padding(4.dp)
                                )
                            }
                        }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Rounded.AccountCircle,
                            contentDescription = "account",
                            modifier=Modifier.size(30.dp)
                        )
                    }

                    }

                },
                shape = CircleShape,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFB7ECEC),
                    unfocusedContainerColor = Color(0xFFD3F0F0)
                )
            )



           /* Divider(modifier=Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = Color(0xFF000000)
            )*/

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                    //.border(border = BorderStroke(1.dp, color = Color(0xFF000000))),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoteInputText(
                    modifier = Modifier.padding(
                        top = 9.dp, bottom = 8.dp
                    ),
                    text = title,
                    label = "Title",
                    maxLine = 1,
                    onTextChange =
                    {
                       title = it
                    }

                )

                Spacer(modifier = Modifier.height(10.dp))


                NoteInputText(
                    modifier = Modifier.padding(
                        top = 9.dp, bottom = 8.dp
                    ),
                    text = description,
                    label = "Add a Note",
                    maxLine = 10,
                    onTextChange = {
                        description = it
                    },
                    imeAction = ImeAction.Default
                )

                /*
                this is for color changing of the tepmplates
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
*/

                Spacer(modifier = Modifier.height(20.dp))

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

            if (noteShow) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    modifier = Modifier.padding(5.dp)
                ) {
                    items(notes) { note ->
                        NoteRow(note = note, onNoteClicked = { onRemoveNote(note) })

                    }
                }
            }


            /*this is lazyVerticalGrid

            LazyVerticalGrid(
                 columns = GridCells.Fixed(2),
                 modifier = Modifier,
                 contentPadding = PaddingValues(5.dp)
             ) {
                 items(notes) { note ->
                     NoteRow(note = note, onNoteClicked = {
                         onRemoveNote(note)
                     })

                 }
             }*/
            else {
                LazyColumn(modifier = Modifier) {
                    items(notes) { note ->
                        NoteRow(note = note,
                            onNoteClicked = {
                                onRemoveNote(note)
                            })
                    }
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
    onNoteClicked: (Note) -> Unit
) {

    val color: Color= Color(0xFFB9ECF5)

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

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                IconButton(
                    onClick = { onNoteClicked(note) },
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Delete,
                        contentDescription = "Delete",
                        tint = Color.Black,
                        modifier = Modifier
                    )
                }
            Row(modifier= Modifier
                .fillMaxWidth()
                .height(45.dp)) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.headlineSmall
                )

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