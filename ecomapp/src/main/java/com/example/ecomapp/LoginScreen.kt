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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ecomapp.signup.AuthViewModel
import com.example.ecomapp.utils.CommonButton
import com.example.ecomapp.utils.CommonVericalSpacer
import com.example.ecomapp.utils.ShowToast
import com.example.ecomapp.utils.isEmailValid
import com.example.ecomapp.utils.isPwdError


//common login pwd  = Admin@1234
@Composable
fun LoginScreen(modifier: Modifier,
                navController: NavHostController,
                authViewModel: AuthViewModel = viewModel()) {
    var context = LocalContext.current

    var emailLogin by remember {
        mutableStateOf("")
    }
    var pwd by remember {
        mutableStateOf("")
    }

    var errorMsg by remember {
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
            text = "Welcome to Login ", color = Color.Blue,
            fontSize = 20.sp,
            fontFamily = FontFamily.Serif,
            modifier = modifier.fillMaxWidth()
        )

        Text(
            text = stringResource(R.string.signinhere), color = Color.Blue,
            fontSize = 18.sp,
            fontFamily = FontFamily.Serif,
            modifier = modifier.fillMaxWidth()
        )
        CommonVericalSpacer(10.dp)
        Image(
            painter = painterResource(R.drawable.ic_signup),
            contentDescription = "Login image ",
            modifier.size(200.dp)
        )
        CommonVericalSpacer(10.dp)
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
            modifier = modifier.fillMaxWidth()
        )
        CommonVericalSpacer(10.dp)

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
            modifier = modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        CommonVericalSpacer(10.dp)

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

@Composable
fun disply(s: String) {
    Text(text = "s")
}
