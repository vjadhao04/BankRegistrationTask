package com.bank.bankregistrationtask.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateValidater {
     fun isValidDOB(day: String, month: String, year: String): Boolean {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        dateFormat.isLenient = false

        return try {
            val dateStr = "$day/$month/$year"
            val date = dateFormat.parse(dateStr)
            // Check if the date is not in the future
            if (date.after(Date())) {
                return false
            }
            // Optional: Check if the date is not too far in the past
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, -120) // Assuming age cannot be more than 120 years
            if (date.before(calendar.time)) {
                return false
            }
            true
        } catch (e: ParseException) {
            false
        } catch (e: IllegalArgumentException) {
            false
        }
    }
}