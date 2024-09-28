package com.example.notess.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.notess.NotesApplication
import com.example.notess.ui.edit.EditScreenViewModel
import com.example.notess.ui.home.HomeViewModel

object AppViewModelProvider {
    val factory = viewModelFactory {
        initializer {
            HomeViewModel(
                notesApplication().container.notesRepository,
                notesApplication().container.trashRepository
            )
        }
        initializer {
            EditScreenViewModel(
                notesApplication().container.notesRepository
            )
        }
    }
}

fun CreationExtras.notesApplication() : NotesApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NotesApplication)