package com.example.notess.data

import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(private val notesDao: NotesDao) : NotesRepository {
    override suspend fun insertNote(note: Note) {
        notesDao.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        notesDao.updateNote(note)
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(note)
    }

    override suspend fun updateFavouriteStatus(id: Int, isFavourite: Boolean) {
        notesDao.updateFavouriteStatus(id,isFavourite)
    }

    override suspend fun updatePinnedStatus(id: Int, isPinned: Boolean) {
        notesDao.updatePinnedStatus(id,isPinned)
    }

    override suspend fun moveNoteToTrash(id: Int, deletedAt: Long) {
        notesDao.moveNoteToTrash(id,deletedAt)
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes()
    }

    override fun getNoteById(id: Int): Flow<Note> {
        return notesDao.getNoteById(id)
    }

    override fun getFavouriteNotes(): Flow<List<Note>> {
        return notesDao.getFavouriteNotes()
    }

    override fun getPinnedNotes(): Flow<List<Note>> {
        return notesDao.getPinnedNotes()
    }

    override suspend fun updateNoteColor(id: Int, color: Int) {
        notesDao.updateNoteColor(id,color)
    }

}