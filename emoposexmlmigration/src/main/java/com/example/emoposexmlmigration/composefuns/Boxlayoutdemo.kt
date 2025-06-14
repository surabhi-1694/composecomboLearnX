package com.example.emoposexmlmigration.composefuns

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.emoposexmlmigration.R
import com.example.emoposexmlmigration.utils.CommonVericalSpacer

@Composable
fun BoxLayoutDemo(){
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState(), enabled = true)
        ) {
            Text(text = "Display Demo of box layout as frame layout ", modifier = Modifier.padding(horizontal = 10.dp))
            CommonVericalSpacer(5.dp)
            repeat(30) {
                Button(onClick = {
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)) {
                    Text(text = "Button ")
                }
            }
            CommonVericalSpacer(5.dp)
            Button(onClick = {
            }, modifier = Modifier.background(color = Color.Green)) {
                Text(text = "Button Green")
            }
        }



        Button(onClick = {
        }, colors = ButtonDefaults
            .buttonColors(containerColor = colorResource(R.color.teal_201))
            ,modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomEnd)) {
            Box(
                modifier = Modifier
                    .background(color = colorResource(R.color.greyblack_color))
                    .padding(20.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Button Grey")

            }

        }

    }
}