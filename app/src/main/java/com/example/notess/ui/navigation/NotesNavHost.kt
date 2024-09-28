package com.example.notess.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.notess.ui.edit.EditScreen
import com.example.notess.ui.edit.EditScreenDestination
import com.example.notess.ui.home.HomeDestination
import com.example.notess.ui.home.HomeScreen


@Composable
fun NotesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = HomeDestination.route
    ){
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToAddNote = { navController.navigate(EditScreenDestination.routeForNewNote) },
                navigateToEditNote = { navController.navigate("${EditScreenDestination.route}/$it") }
            )
        }
        composable(
            route = EditScreenDestination.routeWithArgs,
            arguments = listOf(navArgument(EditScreenDestination.noteIdArg) {
                type = NavType.IntType
            })
        ) {
            EditScreen(
                noteId = it.arguments?.getInt(EditScreenDestination.noteIdArg),
                navigateUp = { navController.navigateUp() })
        }
        composable(route = EditScreenDestination.routeForNewNote) {
            EditScreen(noteId = null, navigateUp = {navController.navigateUp()})
        }
    }

}