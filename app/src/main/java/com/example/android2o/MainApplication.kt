package com.example.android2o

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.android2o.database.AppDataBase

class MainApplication:Application() {

    companion object{
        var instance:MainApplication? = null

        fun getAppContext():Context{
            return instance!!.applicationContext
        }

        lateinit var todoDataBase: AppDataBase

    }
    init {
        instance = this
    }

    private fun getAppDataBaseObj(){
        todoDataBase=  Room.databaseBuilder(
            context = getAppContext(),
            AppDataBase::class.java,"TODO_DB").build()
    }

    override fun onCreate() {
        super.onCreate()
        getAppDataBaseObj()
    }
}