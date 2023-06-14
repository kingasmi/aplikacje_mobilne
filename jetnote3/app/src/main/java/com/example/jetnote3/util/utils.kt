package com.example.jetnote3.util

import com.example.jetnote3.model.Note
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(time: Note): String {
    val date = Date(time)
    val format = SimpleDateFormat("EE, d MMM hh:mm aaa", Locale.getDefault())
    return format.format(date)
}