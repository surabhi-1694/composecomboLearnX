package com.example.ecomapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecomapp.utils.CommonButton
import com.example.ecomapp.utils.CommonSpacer
import com.example.ecomapp.utils.isEmailValid
import com.example.ecomapp.utils.isPwdValid

@Composable
fun SignUpScreen(modifier: Modifier, navController: NavHostController) {


    var email by remember {
        mutableStateOf("")
    }
    var fullName by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var errorMsg by remember {
        mutableStateOf<String?>(null)
    }
    var errorMsgname by remember {
        mutableStateOf<String?>(null)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            modifier = modifier.fillMaxWidth(),
            color = Color.Blue,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            text = "Hello There!"
        )
        CommonSpacer(5.dp)
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp),
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            text = stringResource(R.string.welcome)
        )
        CommonSpacer(20.dp)
        Image(
            painterResource(R.drawable.ic_signup),
            contentDescription = "Sign up",
            modifier = modifier.size(200.dp)
        )
        CommonSpacer(20.dp)
        //Email
        OutlinedTextField(
            value = email, onValueChange = { onValueChange ->
                email = onValueChange
            },
            label = {
                Text(text = stringResource(R.string.Email))
            },
            isError = isEmailValid(email),
            supportingText = {
                if (isEmailValid(email)){
                    Text(text = stringResource(R.string.Email_valid))
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
        CommonSpacer(10.dp)
        //Full name
        //todo check
        OutlinedTextField(
            value = fullName, onValueChange = { onValueChange ->
                fullName = onValueChange
                errorMsgname = onValueChange
            },
            label = {
                Text(text = stringResource(R.string.FullName))
            },
            isError = errorMsgname != null,
            supportingText = {
                if(errorMsgname?.isEmpty() == true){
                    Text(text = "fullName can not be blank", color = Color.Red)
                }
            }, modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        CommonSpacer(10.dp)
        //password
        OutlinedTextField(
            value = password, onValueChange = { onValueChange ->
                password = onValueChange
                errorMsg = isPwdValid(password)
            },
            label = {
                Text(text = stringResource(R.string.Password))
            },
            isError = errorMsg != null,
            supportingText = {
                if (errorMsg != null) {
                    Text(
                        text = errorMsg!!,
                        color = Color.Red
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        CommonSpacer(40.dp)

        CommonButton(stringResource(R.string.signUpButton)) { //onClick
            navController.navigate(LoginRoute)
        }
    }

}