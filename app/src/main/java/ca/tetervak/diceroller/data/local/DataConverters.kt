package ca.tetervak.diceroller.data.local

import androidx.room.TypeConverter
import java.util.Date

class DataConverters {

    @TypeConverter
    fun convertFromLongToDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun convertFromDateToLong(date: Date): Long {
        return date.time
    }
}