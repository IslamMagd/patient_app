package com.islam.patient.util
import android.util.Log
import com.islam.domain.model.DoctorAvailability
import java.text.SimpleDateFormat
import java.util.*

fun adjustDaysToStartFromToday(availabilityList: List<DoctorAvailability>): List<DoctorAvailability> {
    val today = Calendar.getInstance()
    val dayFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)

    val adjustedList = mutableListOf<DoctorAvailability>()

    for (i in 0..6) {
        val dayOfWeek = dayFormat.format(today.time)
        val date = dateFormat.format(today.time)
        val availability = availabilityList.find { it.available ==true }

        if (availability != null) {
            adjustedList.add(
                availability.copy(
                    id = availability.id,
                    day = "$dayOfWeek $date",
                    startTime = availability.startTime,
                    endTime = availability.endTime
                )
            )
        }
        today.add(Calendar.DAY_OF_YEAR, 1)
    }
    Log.d("seeAvailablites","print$adjustedList")

    return adjustedList
}