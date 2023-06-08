package com.example.jetnote3.data

import com.example.jetnote3.model.Note
import com.example.jetnote3.util.UUIDConverter

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConvertes(DataConverter::class, UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase {
    abstract fun noteDao(): NoteDatabaseDao
}