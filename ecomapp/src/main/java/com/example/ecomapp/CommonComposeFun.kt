package com.example.ecomapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CommonButton(text:String,onClick:()-> Unit){
    Button(modifier = Modifier.fillMaxWidth().padding(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.teal_201), // Background color
            contentColor = colorResource(id = R.color.teal_700) // Text color
        ),
        onClick = {
            onClick()
    }) {
        Text(modifier = Modifier.padding(10.dp), text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
    }
}

@Composable
fun CommonSpacer(spaceValue: Dp) {
    Spacer(modifier = Modifier.height(spaceValue))
}
//
//fun ShowToast(context: Context,text: String){
//    Toast.makeText(LocalContext.current,text,Toast.LENGTH_LONG).show()
//}