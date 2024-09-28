package com.example.notess.data

import kotlinx.coroutines.flow.Flow

class TrashedNotesRepositoryImpl(private val trashedNoteDao: TrashedNoteDao) : TrashedNotesRepository {
    override suspend fun insertTrashedNote(note: TrashedNotes) {
        trashedNoteDao.insertTrashedNote(note)
    }

    override suspend fun deleteTrashedNote(note: TrashedNotes) {
        trashedNoteDao.deleteTrashedNote(note)
    }

    override fun getAllTrashedNotes(): Flow<List<TrashedNotes>> = trashedNoteDao.getAllTrashedNotes()

    override suspend fun permanentlyDeleteTrashedNote(noteID: Int) {
        trashedNoteDao.permanentlyDeleteTrashedNote(noteID)
    }

    override suspend fun restoreNote(noteID: Int) {
        trashedNoteDao.restoreNote(noteID)
    }
}