package com.example.android2o

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //simple button structure
                Column(modifier = Modifier.fillMaxSize()) {
                    // Scrollable content
                    Column(
                        modifier = Modifier
                            .weight(1f) // Scrollable content takes all remaining space
                            .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                            .padding(16.dp)
                    ) {
                        // Example content
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(90.dp)
                                    .verticalScroll(rememberScrollState()) // Enable horizontal scrolling
                                    .padding(15.dp)
                                    .background(Color.Green)
                            ) {
                                Text(
                                    text = "Title of Compose Title of Compose Title of Compose Title of Compose Title of Compose Title of Compose Title of Compose Title of Compose Title of Compose Title of Compose Title of Compose Title of Compose",
                                    fontSize = 30.sp
                                )
                            }
                            TextField(
                                value = "Enter LastName",
                                onValueChange = {
                                    // Handle text change
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(15.dp)
                            )
                            // Add more content here as needed
                            Text(
                                text = "Additional Content 1",
                                color = Color.Blue,
                                fontSize = 32.sp,
                                modifier = Modifier.padding(8.dp)
                                    .weight(1f)

                            )

                        }

                        // Add more content here as needed
                        Text(
                            text = "Additional Content 1",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                        Text(
                            text = "Additional Content 2",
                            modifier = Modifier.padding(80.dp)
                        )
                    }

                    // Fixed buttons at the bottom
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { /* Handle button click */ }) {
                            Text(text = "Cancel")
                        }

                        Button(onClick = { /* Handle button click */ }) {
                            Text(text = "Submit")
                        }
                    }
                }
            }

        }
    }
