package com.example.jetnote4.data

import com.example.jetnote4.model.Note

class NotesDataSource{
    fun loadNotes():List<Note>{
        return listOf(
            Note(title = "Keep at in", description = "Sometimes"),
            Note(title = "Skarbowi Państwa", description = "Ministerstwa Finansów " +
                    "do przesyłania klientom e-paragonu na telefon.")
        )
    }
}