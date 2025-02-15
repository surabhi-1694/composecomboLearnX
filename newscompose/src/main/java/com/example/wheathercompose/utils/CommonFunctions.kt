package com.example.wheathercompose.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date


@SuppressLint("SimpleDateFormat")
fun ConvertDateFormate(inputFormat:String, outputFormate:String, stringDate:String):String{
    val sdfinput = SimpleDateFormat(inputFormat)
    val sdfoutput = SimpleDateFormat(outputFormate)

    val outputDate:Date = sdfinput.parse(stringDate)

    return sdfoutput.format(outputDate)

}
val inputDateFormat = "yyyy-mm-dd'T'HH:mm:ss'Z'"
val outputDateFormat = "yyyy-mm-dd hh:mm a"