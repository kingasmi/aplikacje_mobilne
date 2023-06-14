package com.example.jetnote3.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import com.example.jetnote3.model.Note
import com.example.jetnote3.repository.NoteRepository
import javax.inject.Inject
import kotlin.text.Typography.dagger

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {
    private var _noteList= MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO) {
            repository.getAllNotes()
                .distinctUntilChanged()
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