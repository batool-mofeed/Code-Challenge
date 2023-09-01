package com.batool.codechallenge.app.util.customviews

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*

/**
 * Created By Batool Mofeed on 8/31/2023.
 **/
@RequiresApi(Build.VERSION_CODES.O)
fun Context.showDatePickerDialog(
    onDateReady: (String, String) -> Unit,
    dateLessThanEighteen: () -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.YEAR,-18)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    DatePickerDialog(
        this,
        { _, yearOfYear, monthOfYear, dayOfMonth ->
            val eighteenYearsAgo = LocalDate.now() - Period.ofYears(18)
            val selectedMonth = monthOfYear + 1
            val viewDate = "$dayOfMonth-$selectedMonth-$yearOfYear"
            val format = SimpleDateFormat("ddMMyyyy")
            val date = format.format(calendar.time)

            Timber.e("date to send $date")
            val pickedDate = LocalDate.of(yearOfYear, monthOfYear + 1, dayOfMonth)
            if (pickedDate >= eighteenYearsAgo) {
                // Picked a date less than 18 years ago
                dateLessThanEighteen()
            } else {
                onDateReady(viewDate, date)
            }
        }, year, month, day
    ).show()
}