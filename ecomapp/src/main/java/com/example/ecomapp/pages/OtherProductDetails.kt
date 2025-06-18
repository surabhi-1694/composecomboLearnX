package com.example.ecomapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecomapp.Home.CategoryWiseData

@Composable
fun OtherProductDetails(videoItemData: CategoryWiseData?) {
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
        //add list in firestore db using map and set in row with for each
        Row {
            Text(text = "ABCD ")

        }

    }
}