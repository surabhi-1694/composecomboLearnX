package com.example.android2o.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities =[TodoDB::class], version = 1, exportSchema = true)
@TypeConverters(ConverterforDB::class)
abstract class AppDataBase:RoomDatabase() {

    companion object{
        const val name = "TODO_DB"
    }

   abstract fun getTodoDao():TodoDao






}