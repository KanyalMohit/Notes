package com.example.notess.ui.home



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notess.ui.AppViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notess.R
import com.example.notess.data.Note
import com.example.notess.ui.navigation.NavigationDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object HomeDestination : NavigationDestination{
    override val route = "home"
    override val titleRes = "Home Screen"
}

enum class  View { Grid , Row}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.factory),
    navigateToAddNote: () -> Unit,
    navigateToEditNote: () -> Unit
) {

    var text by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    var currentView by remember { mutableStateOf(View.Grid) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val uiState by viewModel.homeUiState.collectAsState()

    val noteList = uiState.noteList


    ModalNavigationDrawer(
        drawerState = if (!expanded) drawerState else rememberDrawerState(initialValue = DrawerValue.Closed) ,
        drawerContent = {
            ModalDrawerSheet {
                Text("Notuss App", modifier = Modifier.padding(16.dp))
                HorizontalDivider()
                DrawerContent(modifier = Modifier)
            }
        },
        gesturesEnabled = drawerState.isOpen,
    ) {
        Scaffold(
            topBar = {
                Box(
                   modifier =  if(!expanded){ Modifier.padding(start = 12.dp, end = 12.dp, top = 2.dp) } else Modifier
                ) {
                    SearchBar(
                        modifier = Modifier
                            .fillMaxWidth(),
                        inputField = {
                            SearchBarContent(
                                expanded = expanded,
                                onExpandedChange = {
                                    expanded = it
                                },
                                text = text,
                                onTextChange = {
                                    text = it
                                },
                                currentView = currentView,
                                onCurrentViewChange = {
                                    currentView = it
                                },
                                drawerState = drawerState,
                                scope = scope
                            )
                        },
                        expanded = expanded,
                        onExpandedChange = { expanded = it },
                        content = {

                        },
                        shape = ShapeDefaults.Large,
                        colors = SearchBarDefaults.colors(
                            containerColor = Color(0xFFE8DBFE),
                            dividerColor = Color.Black,
                        )
                    )
                }
            },
            bottomBar = {
                BottomHomeBar(
                    onCreateClick = navigateToAddNote
                    ,
                    onStarClick = {

                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.offset(y = 42.dp, x = (-35).dp),
                    onClick = navigateToAddNote,
                    elevation = FloatingActionButtonDefaults.elevation(
                        defaultElevation = 25.dp,
                        pressedElevation = 100.dp
                    )
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        ) { innerPadding->
            Box(
                modifier = Modifier.padding(innerPadding)
            ) {
                if(currentView == View.Row){
                    LazyColumn(
                        modifier = Modifier.padding(),
                        contentPadding = PaddingValues(7.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(noteList){ note->
                            NoteCard(note, onClick = {})
                        }
                    }
                }else{
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier,
                        columns = StaggeredGridCells.Fixed(2),
                        contentPadding = PaddingValues(12.dp),
                        verticalItemSpacing = 12.dp,
                        horizontalArrangement = Arrangement.spacedBy(9.dp)
                    ) {
                        items(noteList) { note ->
                            NoteCard(note = note, onClick = {})
                        }
                    }
                }

            }

        }
    }
}

@Composable
private fun NoteCard(note: Note, onClick: () -> Unit) {
    OutlinedCard(onClick = { /*TODO*/ }) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Text(text = note.title, style = MaterialTheme.typography.headlineLarge)
            Text(text = note.content, style = MaterialTheme.typography.bodyLarge)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Date Created: ${formatDate(note.timestamp)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


fun formatDate(timeStamp : Long) : String{
    val date = Date(timeStamp)
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}


@Composable
private fun BottomHomeBar(
    onCreateClick: () -> Unit,
    onStarClick: () -> Unit
) {
    BottomAppBar(
        modifier = Modifier.height(45.dp),
        actions = {
            IconButton(
                onClick =
                onCreateClick
            ) {
                Icon(imageVector = Icons.Filled.Create, contentDescription = "Search")
            }
            IconButton(onClick = onStarClick) {
                Icon(
                    Icons.Filled.Star,
                    contentDescription = "Localized description",
                )
            }
        },
        containerColor = Color(0xFF7B5FBB),
        windowInsets = WindowInsets(left = 12.dp, bottom = 9.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarContent(
    expanded : Boolean,
    onExpandedChange: (Boolean) -> Unit,
    text : String,
    onTextChange : (String) -> Unit,
    currentView : View,
    onCurrentViewChange : (View) -> Unit,
    drawerState: DrawerState,
    scope : CoroutineScope
) {
    SearchBarDefaults.InputField(
        modifier = Modifier,
        query = text,
        onQueryChange = onTextChange ,
        onSearch = { onExpandedChange(false) },
        expanded = expanded,
        onExpandedChange = onExpandedChange,

        leadingIcon = {
            if(expanded){
                IconButton(onClick = {
                    onExpandedChange(false)
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }else{
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                    )
                }
            }
        },
        trailingIcon = {
            if(!expanded){
                Row(
                    modifier = Modifier,
                ) {
                    IconButton(onClick = {
                        onCurrentViewChange(if (currentView == View.Grid) View.Row else View.Grid)
                    }) {
                        Icon(
                            painter = painterResource(id = if (currentView == View.Grid) R.drawable.baseline_grid_view_24 else R.drawable.baseline_table_rows_24),
                            contentDescription = "View",
                        )
                    }
                }
            }
        }
    )

}



@Composable
fun DrawerContent(modifier: Modifier = Modifier) {
    NavigationDrawerItem(
        label = { Text(text = "Drawer Item") },
        selected = false,
        onClick = { /*TODO*/ }
    )
}