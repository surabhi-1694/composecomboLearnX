package com.example.android2o.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {

    @Query("SELECT * FROM TodoDB")
    fun getAllToDo():LiveData<List<TodoDB>>

    @Query("delete FROM TodoDB where id =:id")
    fun deleteToDo(id:Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToDo(todoDB: TodoDB)
}