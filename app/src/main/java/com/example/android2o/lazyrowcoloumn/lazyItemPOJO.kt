package com.example.android2o.lazyrowcoloumn

import android.util.Log
import com.example.android2o.R

data class lazyItemPOJO(
    var firstName: String?,
    var lastName: String?,
    var middleName: String?,
    var profileImage: Int
)

fun getLazyItemPOJOLIST(): List<lazyItemPOJO> {
    return List(101){ i->

       val image = if(i % 2 == 0){
            R.drawable.baseline_album_24
        }else{
            R.drawable.composer
        }
        lazyItemPOJO(firstName = "firstName$i",
            lastName = "lastName$i",
            middleName = "middleName$i",
            profileImage = image)
    }
}


