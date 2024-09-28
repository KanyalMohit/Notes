package com.example.notess.data

import androidx.compose.ui.graphics.Color
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("UPDATE notes SET isFavourite = :isFavourite WHERE id= :id")
    suspend fun updateFavouriteStatus(id:Int, isFavourite : Boolean)

    @Query("UPDATE notes SET isPinned = :isPinned WHERE id= :id")
    suspend fun updatePinnedStatus(id:Int, isPinned : Boolean)

    @Query("INSERT INTO trashed_notes (title,content,timeStamp,deletedAt,color)" +
            "SELECT title,content,timestamp, :deletedAt, color From notes WHERE id = :noteID")
    suspend fun moveToTrash(noteID : Int, deletedAt : Long)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id : Int)

    suspend fun moveNoteToTrash(id :Int , deletedAt : Long) {
        moveToTrash(id,deletedAt)
        deleteNoteById(id)
    }

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id : Int) : Flow<Note>

    @Query("SELECT * FROM notes WHERE isFavourite = 1")
    fun getFavouriteNotes() : Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE isPinned = 1")
    fun getPinnedNotes() : Flow<List<Note>>

    @Query("UPDATE notes SET color = :color WHERE id = :id")
    suspend fun updateNoteColor(id: Int, color: Int)

}