package com.example.foodexpiryscanner.utils

import android.content.Context
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {
    private const val DATE_PATTERN = "yyyy-MM-dd"

    fun format(year: Int, month: Int, dayOfMonth: Int): String {
        return String.format(
            Locale.getDefault(),
            "%04d-%02d-%02d",
            year,
            month + 1,
            dayOfMonth
        )
    }

    fun toast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun daysUntil(dateString: String): Int? {
        return try {
            val format = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            format.isLenient = false

            val targetDate = format.parse(dateString) ?: return null
            val today = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            val targetCal = Calendar.getInstance().apply {
                time = targetDate
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            val diffMillis = targetCal.timeInMillis - today.timeInMillis
            (diffMillis / (1000 * 60 * 60 * 24)).toInt()
        } catch (_: Exception) {
            null
        }
    }
}
