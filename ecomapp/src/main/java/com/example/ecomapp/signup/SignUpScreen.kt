package com.example.ecomapp.signup

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ecomapp.AuthRoute
import com.example.ecomapp.HomeRoute
import com.example.ecomapp.R
import com.example.ecomapp.utils.CommonButton
import com.example.ecomapp.utils.CommonVericalSpacer
import com.example.ecomapp.utils.ShowToast
import com.example.ecomapp.utils.isEmailValid
import com.example.ecomapp.utils.isPwdError

@Composable
fun SignUpScreen(modifier: Modifier, navController: NavHostController,authViewModel: AuthViewModel = viewModel() ) {


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

    var isLoading by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current
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
        CommonVericalSpacer(5.dp)
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp),
            color = Color.Blue,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif,
            text = stringResource(R.string.createAccount)
        )
        CommonVericalSpacer(20.dp)
        Image(
            painterResource(R.drawable.ic_signup),
            contentDescription = "Sign up",
            modifier = modifier.size(200.dp)
        )
        CommonVericalSpacer(20.dp)
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
        CommonVericalSpacer(10.dp)
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
             modifier = Modifier.fillMaxWidth(),
        )
        CommonVericalSpacer(10.dp)
        //password
        OutlinedTextField(
            value = password, onValueChange = { onValueChange ->
                password = onValueChange
                errorMsg = isPwdError(password).first
            },
            label = {
                Text(text = stringResource(R.string.Password))
            },
            //todo check
            isError = isPwdError(password).second,
            supportingText = {
                if (isPwdError(password).second) {
                    errorMsg?.let {
                        Text(
                            text = it
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        CommonVericalSpacer(40.dp)

        CommonButton(isEnable = isLoading,text = if(isLoading)"Creating Account " else stringResource(R.string.signUpButton)) {
            //onClick
            isLoading = true
            if(email.isNotEmpty()
                && fullName.isNotEmpty()
                && password.isNotEmpty() ){
                authViewModel.signup(email = email,name = fullName,password = password){ status,msg ->
                    if(status){
                        isLoading = false
                        navController.navigate(HomeRoute){
                            popUpTo<AuthRoute> {
                                inclusive = true
                            }
                        }

                    }else{
                        isLoading = false
                        ShowToast(context,msg?:"Something went wrong")
                    }
                }
            }else{
                ShowToast(context,context.resources.getString(R.string.validation_msg))
            }
//            authViewModel.signup(email= email, name = fullName,password = password, onResult = { s,i->
//
//            })
        }
    }

}