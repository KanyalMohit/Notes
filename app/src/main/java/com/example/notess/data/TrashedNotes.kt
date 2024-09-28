package com.example.notess.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trashed_notes")
data class TrashedNotes(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val timeStamp: Long,
    val deletedAt: Long = System.currentTimeMillis(),
    val color : Int
)