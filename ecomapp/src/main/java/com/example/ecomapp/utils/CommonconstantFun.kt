package com.example.ecomapp.utils

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class DifferentScreenConfig {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE,
    TABLET_PORTRAIT,
    TABLET_LANDSCAPE,
    DESKTOP
}


enum class OrderStatus {
    ORDERED,
    SHIPPED,
    ARRIVED,
    DELIVERED
}


object windowSizeClass {
    fun fromWindowSizeClass(windowSizeClass: WindowSizeClass): DifferentScreenConfig {
        val widthClass = windowSizeClass.windowWidthSizeClass
        val heightClass = windowSizeClass.windowHeightSizeClass

        return when {
            //potrait
            widthClass == WindowWidthSizeClass.COMPACT &&
                    heightClass == WindowHeightSizeClass.MEDIUM -> DifferentScreenConfig.MOBILE_PORTRAIT

            widthClass == WindowWidthSizeClass.COMPACT &&
                    heightClass == WindowHeightSizeClass.EXPANDED -> DifferentScreenConfig.MOBILE_PORTRAIT

            widthClass == WindowWidthSizeClass.EXPANDED &&
                    heightClass == WindowHeightSizeClass.COMPACT -> DifferentScreenConfig.MOBILE_LANDSCAPE

            widthClass == WindowWidthSizeClass.MEDIUM &&
                    heightClass == WindowHeightSizeClass.EXPANDED -> DifferentScreenConfig.TABLET_PORTRAIT

            widthClass == WindowWidthSizeClass.EXPANDED &&
                    heightClass == WindowHeightSizeClass.MEDIUM -> DifferentScreenConfig.TABLET_LANDSCAPE

            else -> DifferentScreenConfig.DESKTOP
        }
    }
}


fun dateToString(date: Timestamp): String {
    val date: Date = date.toDate()


    // Create a SimpleDateFormat with the desired pattern and locale
    val sdf: SimpleDateFormat = SimpleDateFormat("dd/MMM/YYYY", Locale.getDefault())


    // Format the Date object into a string
    return sdf.format(date)

}
