package com.kaizencoder.stackoverflowbrowser.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {

    fun convertTimestampToFormattedDate(timestamp: Int): String {
        // Convert Int to Long (milliseconds) and create a Date object
        val date = Date(timestamp.toLong() * 1000)

        // Create SimpleDateFormat with desired pattern and locale
        val sdf = SimpleDateFormat("MMM dd yyyy 'at' hh:mm a", Locale.getDefault())

        // Format the date to string
        return sdf.format(date)
    }
}