package com.example.ecomapp.utils

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecomapp.AuthRoute
import com.example.ecomapp.HomeRoute
import com.example.ecomapp.R
import com.example.ecomapp.signup.AuthViewModel
@Composable
fun commonLoginField(modifier: Modifier,
                     authViewModel: AuthViewModel,
                     navController: NavHostController,
                     context:Context
                     ){

    var emailLogin by remember {
        mutableStateOf("")
    }

    var pwd by remember {
        mutableStateOf("")
    }

    var errorMsg by remember {
        mutableStateOf<String?>(null)
    }
    CommonVericalSpacer(10.dp)

    Column(modifier) {

        //Email login Id
        OutlinedTextField(
            value = emailLogin, onValueChange = {
                emailLogin = it
            }, label = {
                Text(text = "Email")
            }, isError = isEmailValid(emailLogin), supportingText = {
                if (isEmailValid(emailLogin)) {
                    Text(text = stringResource(R.string.Email_valid))
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        CommonVericalSpacer(5.dp)

        //Password Login
        OutlinedTextField(
            value = pwd, onValueChange = { valuechange ->
                pwd = valuechange
                errorMsg = isPwdError(pwd).first

            }, label = {
                Text("Password")
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
        CommonVericalSpacer(5.dp)

        CommonButton(
            text = stringResource(
                R.string.loginButton
            )
        ) {
            //onclick
            authViewModel.login(emailLogin,pwd){authResult,status,msg->
                if(status){

                    navController.navigate(HomeRoute){
                        popUpTo<AuthRoute> {
                            inclusive = true
                        }
                    }
                }else{
                    ShowToast(context = context,"Something went wrong")
                }

            }

        }
    }

}


//to set size of colum use parent's modifier as it is sub child ,
// But when creating view inside this sub view use it's own modifier as now it's parent is subview
// EX: here Colum is using it's parent's modifier in order to set in view But
// Text() composable will use it's own modifier as now it has now it's own space

@Composable
fun commonLoginHeader(modifier: Modifier){
    Column(modifier = modifier,
        ) {
        Text(
            text = "Welcome to Login ", color = Color.Blue,
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.fillMaxWidth()
      )
        Text(
            text = stringResource(R.string.signinhere), color = Color.Blue,
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(R.drawable.ic_signup),
            contentDescription = "Login image ",
            modifier.size(200.dp)
        )
    }

}