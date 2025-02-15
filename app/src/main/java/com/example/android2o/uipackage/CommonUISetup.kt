package com.example.android2o.uipackage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Spacer(){

}
@Composable
fun Spacer1(){
    Spacer(Modifier.height(1.dp))
}


@Composable
fun Spacer8(){
    Spacer(Modifier.height(8.dp))
}

@Composable
fun SpacerValue(value:Int){
    Spacer(Modifier.height(value.dp))
}

@Composable
fun CircularProgressBar(color: Color, i: Int) {
    Box(modifier = Modifier.fillMaxWidth().height(i.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = color )
    }
}