package com.example.notes.di

import android.content.Context
import androidx.room.Room
import com.example.notes.data.NoteDatabase
import com.example.notes.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao
            = noteDatabase.noteDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase
            = Room.databaseBuilder(
        context,
        NoteDatabase::class.java,
        "notes_db")
        .fallbackToDestructiveMigration()
        .build()
}