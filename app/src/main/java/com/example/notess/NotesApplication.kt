package com.example.notess

import android.app.Application
import com.example.notess.data.AppContainer
import com.example.notess.data.AppDataContainer

class NotesApplication : Application(){
    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }

}