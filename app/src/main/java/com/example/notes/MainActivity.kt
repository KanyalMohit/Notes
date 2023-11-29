package com.example.notes

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.screen.NoteViewModel
import com.example.notes.screen.NotesScreen
import com.example.notes.ui.theme.NotesTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(statusBarStyle = SystemBarStyle.light(scrim = Color.BLACK, darkScrim = Color.GRAY))
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Log.d("MainActivity", "ViewModel initialized: hoga abi")
                    val noteViewModel = viewModel<NoteViewModel>()
                    Log.d("MainActivity", "ViewModel initialized: $noteViewModel")
                    NotesApp(noteViewModel = noteViewModel)

                }
            }
        }
    }
}

@Composable
fun NotesApp(noteViewModel: NoteViewModel) {

    val notesList = noteViewModel.noteList.collectAsState().value

    NotesScreen(notes = notesList,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = { noteViewModel.removeNote(it) })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesTheme {
        
    }
}