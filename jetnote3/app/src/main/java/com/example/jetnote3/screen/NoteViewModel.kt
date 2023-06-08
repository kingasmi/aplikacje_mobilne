package com.example.jetnote3.screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnote3.data.NotesDataSource
import com.example.jetnote3.model.Note
import com.example.jetnote3.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private var _noteList= MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanges()
                .collect { listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) {
                        Log.d("Empty", ": Epmty list")
                    } else {
                        _noteList.value = listOfNotes
                    }
                }
        }
    }
    fun addNote(note:Note) = viewModelScope.launch {
        repository.addNote(note)
    }
    fun updateNote(note:Note) = viewModelScope.launch {
        repository.updateNote(note)
    }
    fun removeNote(note:Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

}