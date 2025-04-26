package com.example.ecomapp.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomapp.R


@Composable
fun getToastMsg(msg: String): String {
    return msg
}


fun isEmailValid(email:String):Boolean{
    val emailHasErrors by derivedStateOf {
        if (email.isNotEmpty()) {
            // Email is considered erroneous until it completely matches EMAIL_ADDRESS.
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            false
        }
    }
    return emailHasErrors
}

fun isPwdValid(pwd:String):Boolean{
    val pwdValid by derivedStateOf {
        if(pwd.isNotEmpty()){
            //

        }
    }
    return true
}



@Composable
fun CommonButton(text:String,onClick:  ()-> Unit){
    Button(modifier = Modifier.fillMaxWidth().padding(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.teal_201), // Background color
            contentColor = colorResource(id = R.color.teal_700) // Text color
        ),
        onClick = onClick
    ) {
        Text(modifier = Modifier.padding(10.dp), text = text,
            textAlign = TextAlign.Center,
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
    }
}

@Composable
fun CommonSpacer(spaceValue: Dp) {
    Spacer(modifier = Modifier.height(spaceValue))
}
//
fun ShowToast(context: Context,text: String){
    Toast.makeText(context,text,Toast.LENGTH_LONG).show()
}