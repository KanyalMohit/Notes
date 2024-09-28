package com.example.notess.data

import kotlinx.coroutines.flow.Flow

interface TrashedNotesRepository {
    suspend fun insertTrashedNote(note : TrashedNotes)

    suspend fun deleteTrashedNote(note: TrashedNotes)

    fun getAllTrashedNotes() : Flow<List<TrashedNotes>>

    suspend fun permanentlyDeleteTrashedNote(noteID : Int)

    suspend fun restoreNote(noteID : Int)

}