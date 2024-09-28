package com.example.notess.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrashedNoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrashedNote(note : TrashedNotes)

    @Delete
    suspend fun deleteTrashedNote(note : TrashedNotes)

    @Query("SELECT * FROM trashed_notes")
    fun getAllTrashedNotes() : Flow<List<TrashedNotes>>

    @Query("Delete FROM trashed_notes WHERE id = :noteID")
    suspend fun permanentlyDeleteTrashedNote(noteID : Int)

    @Query("INSERT INTO notes (title,content,timestamp,color)" +
            "SELECT title,content,timeStamp,color FROM trashed_notes WHERE id = :noteID")
    suspend fun restoreNote(noteID: Int)

}