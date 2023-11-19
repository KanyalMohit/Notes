package com.example.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notes.model.Note
import com.example.notes.util.DateConverter
import com.example.notes.util.UUIDConverter

@Database(entities = [Note::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class,UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDatabaseDao

}