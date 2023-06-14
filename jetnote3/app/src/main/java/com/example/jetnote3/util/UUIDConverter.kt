package com.example.jetnote3.util

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID?): String?{
        return uuid.toString()
    }

    @TypeConverter
    fun fromFromString(string: String?): UUID?{
        return UUID.fromString(string)
    }
}