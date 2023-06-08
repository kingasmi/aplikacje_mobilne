package com.example.jetnote3.data

import com.example.jetnote3.model.Note

class NotesDataSource {
    fun loadNotes():List<Note>{
        return listOf(
            Note(title = "Mobilne", description = "Wykład"),
            Note(title = "Skarbowi Państwa", description = "Ministerstwo finansow do przesylania klientom e-paragonu na telefon."),

            )
    }
}