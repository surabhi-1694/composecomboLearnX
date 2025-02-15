package com.example.android2o.database

import android.util.Log
import androidx.room.TypeConverter
import java.util.Date

class ConverterforDB {

    @TypeConverter
    fun DatetoLong(date:Date):Long{
        return date.time
    }

    @TypeConverter
    fun LongtoDate(longDate:Long):Date{
        return Date(longDate)

    }
}
