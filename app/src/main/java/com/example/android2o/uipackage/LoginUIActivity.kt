package com.example.android2o.uipackage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.android2o.R
import com.example.android2o.route.Routes.loginScreen_next

//login screen with argument passed
@Composable
fun LoginScreenWithNavigation(navController: NavHostController) {
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome Login", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        SpacerValue(5)
        Button(onClick = {
            Toast.makeText(context, "Navigate to next screen ", Toast.LENGTH_LONG).show()
            navController.navigate(loginScreen_next)
        }) {
            Text(text = stringResource(R.string.btnNext))
        }

        Button(onClick = {
            //here username is actual value enter by user or whatever value need to pass between two screen
            navController.navigate(loginScreen_next+"/UserName")

        }){
            Text(text = stringResource(R.string.btnNextWithArgs))
        }
    }
}

//next screen data receiver
@Composable
fun NavigationDataReceiver(name:String){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Received Text is $name")
    }
}


// login screen
@Composable
fun LoginScreen() {
    val context = LocalContext.current

    var pwd by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
        Image(
            painter = painterResource(id = R.drawable.composer),
            contentDescription = "Login Page",
            modifier = Modifier.size(200.dp)
        )

        Text(text = "Welcome Login", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Login To Your Account", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        SpacerValue(8)


        OutlinedTextField(value = "", onValueChange = {

        }, label = { Text(text = stringResource(R.string.email_address)) })
        SpacerValue(8)
        Box {
            Column {
                OutlinedTextField(value = pwd, onValueChange = {
                    pwd = it
                }, label = { Text(text = stringResource(R.string.password)) },
                    visualTransformation = PasswordVisualTransformation()
                )
                SpacerValue(5)
                //clickable with with TextButton

                //to set privacy policy text at right side of and bottom of password
                // we need to create box in which password is set and then
                TextButton(onClick = {}) {
                    Text(
                        text = "Forgot Password", color = Color.Red,
                    )
                }
                Text(text = "Privacy Policy", color = Color.Blue, modifier = Modifier.clickable {
                    //do anything
                    Toast.makeText(context, "Login Policy Button ", Toast.LENGTH_LONG).show()
                }.align(Alignment.End))
            }


        }

        SpacerValue(5)
        Button(onClick = {
            Toast.makeText(context, "Login via Button ", Toast.LENGTH_LONG).show()
        }) {
            Text(text = stringResource(R.string.login))
        }
        SpacerValue(5)
        Button(onClick = {
            Toast.makeText(context, "Login via Login Button ", Toast.LENGTH_LONG).show()
        }) {
            Text(text = stringResource(R.string.email_address))
        }
        SpacerValue(5)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Image(
                painter = painterResource(id = R.drawable.baseline_album_24),
                contentDescription = "Google", colorFilter = ColorFilter.tint(Color.Blue)
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_album_24),
                contentDescription = "Facebook", colorFilter = ColorFilter.tint(Color.Black)
            )
            Image(
                painter = painterResource(id = R.drawable.baseline_album_24),
                contentDescription = "Twitter"
            )
        }

    }
}
