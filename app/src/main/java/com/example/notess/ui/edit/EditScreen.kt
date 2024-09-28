package com.example.notess.ui.edit

import android.provider.CalendarContract
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notess.R
import com.example.notess.ui.AppViewModelProvider
import com.example.notess.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object EditScreenDestination : NavigationDestination {
    override val route: String = "edit"
    override val titleRes: String = "Edit Note"
    const val noteIdArg = "noteId"
    val routeWithArgs = "$route/{$noteIdArg}"
    const val routeForNewNote = "newNote"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    noteId: Int?,
    viewModel: EditScreenViewModel = viewModel(factory = AppViewModelProvider.factory),
    navigateUp: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember {
        mutableStateOf(false)
    }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = if (noteId == null) "New Note" else "Edit Note") },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            viewModel.insertNote()
                            navigateUp()
                        }
                    }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate up"
                        )
                    }
                })
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(60.dp),
                actions = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            BottomButton(
                                id = R.drawable.baseline_add_circle_outline_24,
                                contentDescription = "Add More",
                                modifier = Modifier,
                                onClick = {}
                            )
                            BottomButton(
                                id = R.drawable.baseline_color_lens_24,
                                contentDescription = "Color",
                                onClick = { /*TODO*/ })
                            BottomButton(
                                id = R.drawable.outline_font_download_24,
                                contentDescription = "Font",
                                onClick = { /*TODO*/ })
                        }
                        Row {
                            BottomButton(
                                id = R.drawable.baseline_more_vert_24,
                                contentDescription = "More",
                                onClick = { /*TODO*/ })
                        }
                    }
                },
                windowInsets = WindowInsets(left = (-6).dp)
            )
        }
    ) { innerPadding ->

        BackHandler {
            coroutineScope.launch {
                showDialog = true
            }
        }
        if (showDialog) {
                AlertDialog(
                    title = { Text(text = "Save Changes?") },
                    text = { Text(text = "Do you want to save your changes before going back?") },
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Button(onClick = {coroutineScope.launch {
                            viewModel.insertNote()
                            navigateUp()
                        }
                            showDialog = false
                        }) {
                            Text(text = "Save")
                        }
                    },
                    dismissButton = {
                        Button(onClick = {
                            navigateUp()
                            showDialog = false
                        }) {
                            Text(text = "Discard")
                        }
                    }
                    )
            }

        NotesEntryBody(
            noteDetails = viewModel.editScreenDetails,
            onNoteValueChange = viewModel::updateUiState,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
@Composable
private fun BottomButton(
    id: Int,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            painter = painterResource(id = id),
            contentDescription = contentDescription
        )
    }
}

@Composable
fun NotesEntryBody(
    noteDetails: EditScreenDetails,
    onNoteValueChange: (EditScreenDetails) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = noteDetails.title,
                onValueChange = { onNoteValueChange(noteDetails.copy(title = it)) },
                label = { Text(text = "Title") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                ),
                maxLines = 1,
                textStyle = MaterialTheme.typography.headlineLarge
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(700.dp),
                value = noteDetails.content,
                onValueChange = {
                    onNoteValueChange(noteDetails.copy(content = it))
                },
                label = { Text(text = "Content") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent
                )
            )
        }
    }
}



