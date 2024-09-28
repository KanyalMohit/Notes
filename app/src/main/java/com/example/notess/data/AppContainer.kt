package com.example.notess.data

import android.content.Context

interface AppContainer{
    val notesRepository : NotesRepository
    val trashRepository : TrashedNotesRepository
}

class AppDataContainer(private val context : Context) : AppContainer {
    override val notesRepository: NotesRepository by lazy{
           NotesRepositoryImpl(NotesDatabase.getDatabase(context).notesDao())
    }
    override val trashRepository: TrashedNotesRepository by lazy {
            TrashedNotesRepositoryImpl(NotesDatabase.getDatabase(context).trashedNoteDao())
    }
}