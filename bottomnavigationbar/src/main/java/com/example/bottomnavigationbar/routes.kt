package com.example.bottomnavigationbar

import kotlinx.serialization.Serializable

@Serializable
object ScreenMain

@Serializable
object ScreenHome

@Serializable
object ScreenNotification


@Serializable
object ScreenSetting

@Serializable
data class ScreenList(
    val name:String?,
    val age:Int?,
    val address:String?

)