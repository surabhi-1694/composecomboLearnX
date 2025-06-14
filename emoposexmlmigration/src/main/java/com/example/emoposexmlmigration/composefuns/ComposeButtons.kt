package com.example.emoposexmlmigration.composefuns

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.emoposexmlmigration.R


@Composable
fun composeMigrateView(text: String){
    Column(modifier = Modifier.fillMaxWidth()){
        Text(text = text, color = Color.Blue, textAlign = TextAlign.Center)
    }
}

@Composable
fun composeButtonUI(btntext:String,onClickCallBack:()->Unit){
    Button(onClick =  onClickCallBack, modifier = Modifier) {
        Text(text = btntext,
            color = Color.White,
            textAlign = TextAlign.Center)
    }

}