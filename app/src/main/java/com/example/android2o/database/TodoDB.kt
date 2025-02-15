package com.example.android2o.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date



@Entity
data class TodoDB(@PrimaryKey(autoGenerate = true)
                  var id:Int = 0,
                  var title :String,
                  var createdAt: Date)