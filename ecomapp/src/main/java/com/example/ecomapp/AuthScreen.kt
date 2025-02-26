package com.example.ecomapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun AuthScreen(modifier: Modifier, navController: NavHostController) {
    val context = LocalContext.current
    Column(modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(R.drawable.ic_shopping),
            contentDescription = "splashscreen")
        CommonSpacer(5.dp)
        Text(text = stringResource(R.string.splash_info),
            textAlign = TextAlign.Center, style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
        )
        CommonSpacer(5.dp)
        Text(text = stringResource(R.string.splash_sub_info),
            textAlign = TextAlign.Center, fontSize = 20.sp)
        CommonSpacer(5.dp)
        CommonButton(stringResource(R.string.loginButton)) {
            //click event
            Log.e("AuthScreen", "Login Button Click")
            Toast.makeText(context,"Login Button Click", Toast.LENGTH_LONG).show()
            navController.navigate(LoginRoute)
        }
        CommonSpacer(5.dp)
        CommonButton(stringResource(R.string.signUpButton)) {
            //click event
            Log.e("AuthScreen", "signUp Button Click")
            Toast.makeText(context,"signUp Button Click", Toast.LENGTH_LONG).show()
            navController.navigate(SignUpRoute)

        }
    }

}