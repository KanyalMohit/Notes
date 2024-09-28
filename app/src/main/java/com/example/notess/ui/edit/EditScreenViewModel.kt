package com.example.notess.ui.edit

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.notess.data.Note
import com.example.notess.data.NotesRepository





class EditScreenViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    var editScreenDetails by mutableStateOf(EditScreenDetails())
        private set

    fun updateUiState(newDetails: EditScreenDetails) {
        editScreenDetails = newDetails
    }

    suspend fun insertNote() {
        Log.e(TAG, "insertNote: Note inserted", )
        if(NoteValidation()){
            notesRepository.insertNote(note = editScreenDetails.toNote())
        }
    }
    private fun NoteValidation() : Boolean{
        val note = editScreenDetails.toNote()
        if(note.title.isEmpty() && note.content.isEmpty()){
            return false
        }else{
            return true
        }
    }
}


data class EditScreenDetails(
    val title: String = "",
    val content: String = "",
    val timeStamp: Long = System.currentTimeMillis(),
    val isFavourite: Boolean = false,
    val isPinned: Boolean = false,
    val color: Int = Color.Transparent.hashCode()
)

fun EditScreenDetails.toNote() : Note = Note(
    title =title,
    content = content,
    timestamp = timeStamp,
    isPinned = isPinned,
    isFavourite = isFavourite,
    color = color
)

fun Note.toEditScreenDetails() : EditScreenDetails = EditScreenDetails(
    title = title,
    content = content,
    timeStamp = timestamp,
    isPinned = isPinned,
    color = color
    )

