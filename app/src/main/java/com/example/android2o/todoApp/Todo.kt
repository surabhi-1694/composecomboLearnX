package com.example.android2o.todoApp

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date

data class Todo(var id:Int,var title :String,var createdAt: Date)


@RequiresApi(Build.VERSION_CODES.O)
fun getTodoList():List<Todo>{
    return listOf<Todo>(
        Todo(1,"Wake up at 5 am",Date.from(Instant.now())),
        Todo(2,"Break fast",Date.from(Instant.now())),
        Todo(3,"Yoga",Date.from(Instant.now())),
        Todo(4,"Office",Date.from(Instant.now())),
        Todo(5,"lunch",Date.from(Instant.now())),
        Todo(6,"tea break",Date.from(Instant.now())),
        Todo(7,"evening walk ",Date.from(Instant.now())),
        Todo(8,"Gym",Date.from(Instant.now())),
        Todo(9,"Prepare dinner",Date.from(Instant.now())),
        Todo(10,"Dinner",Date.from(Instant.now())),
        Todo(11,"Android Practice",Date.from(Instant.now()))
    )
}