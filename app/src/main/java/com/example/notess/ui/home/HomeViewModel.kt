package com.example.notess.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notess.data.Note
import com.example.notess.data.NotesRepository
import com.example.notess.data.TrashedNotesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(notesRepository: NotesRepository, trashRepository: TrashedNotesRepository) :
    ViewModel() {
    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }
    val homeUiState : StateFlow<HomeUiState> =
        notesRepository.getAllNotes().map { HomeUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = HomeUiState()
            )
}

data class HomeUiState(val noteList: List<Note> = listOf())