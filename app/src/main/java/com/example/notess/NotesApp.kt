package com.example.notess

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notess.ui.navigation.NotesNavHost

@Composable
fun NotesApp(navController: NavHostController = rememberNavController()) {
    NotesNavHost(navController = navController)
}
