package com.example.notess.data

import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insertNote(note: Note)

    suspend fun updateNote(note : Note)

    suspend fun deleteNote(note: Note)

    suspend fun updateFavouriteStatus(id : Int , isFavourite : Boolean)

    suspend fun updatePinnedStatus(id : Int, isPinned : Boolean)

    suspend fun moveNoteToTrash(id : Int , deletedAt : Long)

    fun getAllNotes() : Flow<List<Note>>

    fun getNoteById(id : Int) : Flow<Note>

    fun getFavouriteNotes() : Flow<List<Note>>

    fun getPinnedNotes() : Flow<List<Note>>

    suspend fun updateNoteColor(id : Int , color : Int)

}