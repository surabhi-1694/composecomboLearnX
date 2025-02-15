package com.example.composetheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.composetheme.ui.theme.Android2oTheme

/**
 * we customize theme and can also export color, type,theme or xml files from below link
* https://material-foundation.github.io/material-theme-builder/
* */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Android2oTheme (false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {
        Column(
            modifier = modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = "Big Title ",
                style = MaterialTheme.typography.headlineLarge)
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(modifier = modifier.fillMaxWidth(0.6f),
                    onClick = {}) {
                    Text(text = "Something")
                }
            }
        }
        //Bottom Button To Represent Primary(submit) ,
        // secondary color(Cancel)
        BottomButton(modifier)
//        BottomButtonsize()

    }
}

@Composable
fun BottomButton(modifier: Modifier) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .padding(10.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.BottomStart
    ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp), // Padding inside the Card
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                //primary
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text(
                        text = stringResource(R.string.submit),
                        modifier = modifier,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
                //secondary
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        modifier = modifier,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }


        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    Android2oTheme {
//        Greeting("Android")
//    }
//}