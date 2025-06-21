package com.example.ecomapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomapp.Home.CategoryWiseData
import com.example.ecomapp.utils.CommonHorizontalSpacer
import com.example.ecomapp.utils.CommonVericalSpacer

@Composable
fun OtherProductDetails(videoItemData: CategoryWiseData?) {
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        //add list in firestore db using map and set in row with for each
        videoItemData?.OtherDetails?.forEach{ (key,value)->

            Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                Text(text = "$key :", modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Start, style =
                        TextStyle(color = Color.Blue, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp))
                CommonVericalSpacer(5.dp)
                Text(text = value, modifier = Modifier.padding(4.dp),
                    textAlign = TextAlign.Start,style = TextStyle(color = Color.Blue, fontSize = 15.sp))
            }
        }


    }
}