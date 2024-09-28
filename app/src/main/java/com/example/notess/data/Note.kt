package com.example.notess.data

import android.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isFavourite : Boolean = false,
    val isPinned: Boolean = false,
    val color : Int = Color.TRANSPARENT
)