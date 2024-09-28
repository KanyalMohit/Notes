# Notes App

This is a simple Notes app built with Jetpack Compose for Android.

## Features

* Create and save notes with title and content.
* View a list of saved notes on the home screen.
* Navigate to an edit screen to modify existing notes.
* Basic UI with `OutlinedCard` and `TextField`components.
* State management using ViewModels and `mutableStateOf`.
* Data persistence using a repository pattern (implementation not included).

## Upcoming Features

* Trash functionality for deleted notes.
* Filtering and sorting of notes.
* Enhanced UI with styling and customization options.
* Undo/redo functionality for editing notes.
* Error handling and loading states.

## Technologies Used

* Jetpack Compose
* Kotlin Coroutines
* AndroidX Lifecycle
* Room Database

## Getting Started

1. Clone the repository
2. Open the project inAndroid Studio.
3. Build and run the app on an emulator or device.

## Project Structure

* `ui/home`: Contains composables and ViewModel for the home screen.
* `ui/edit`: Contains composables and ViewModel for the edit screen.
* `data`: contain data models, repositories, and data sources.
