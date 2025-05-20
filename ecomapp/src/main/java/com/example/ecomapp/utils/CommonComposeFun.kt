package com.example.ecomapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomapp.R
import java.util.regex.Pattern


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

fun isFullnameValid(name:String?):Boolean{
    val fullname by derivedStateOf {
        if(name?.isNotEmpty() == true){
            false
        }else{
            true
        }
    }
    return fullname
}


@SuppressLint("SuspiciousIndentation")
fun isPwdError(pwd:String):Pair<String,Boolean>{
    var  validmsgPair:Pair<String,Boolean> = Pair("",false)

    var  validmsg = ""
    val  passwordPattern:String = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
//        if(pwd.isEmpty()){
//            //
//            validmsg = "Password can not be Blank"
//            validmsgPair = Pair(validmsg,false)
//        }else
    if(pwd.isNotEmpty()){
        if(pwd.length <= 8 ){
            validmsg = "Password should be minimum 8 Characters"
            validmsgPair = Pair(validmsg,true)

        }else if(!Pattern.compile(passwordPattern).matcher(pwd).matches()){
            validmsg = "Please add combination of $,*,@,digits etc. "
            validmsgPair = Pair(validmsg,true)
        }else{
            validmsgPair = Pair("",false)

        }
    }
    return validmsgPair
}




@Composable
fun CommonButton(isEnable:Boolean = false,text:String,onClick:()-> Unit){
    Button(modifier = Modifier.fillMaxWidth().padding(5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.teal_201), // Background color
            contentColor = colorResource(id = R.color.teal_700) // Text color
        ),
        enabled = !isEnable,
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
fun CommonVericalSpacer(spaceValue: Dp) {
    Spacer(modifier = Modifier.height(spaceValue))
}

@Composable
fun CommonHorizontalSpacer(spaceValue: Dp) {
    Spacer(modifier = Modifier.height(spaceValue))
}
//
fun ShowToast(context: Context,text: String){
    Toast.makeText(context,text,Toast.LENGTH_LONG).show()
}


//not working custom slidebar
@Composable
fun CustomImageSlider(
    modifier: Modifier = Modifier,
    initialValue: Float = 0f,
    onValueChange: (Float) -> Unit
) {
    var value by remember { mutableStateOf(initialValue) }

    val thumbSize = 50.dp
    val thumbImage = painterResource(id = R.drawable.ic_shopping)
    val density = LocalDensity.current
    val boxWidth = remember { mutableStateOf(70) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(thumbSize)
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val newValue = (offset.x / size.width).coerceIn(0f, 1f)
                    value = newValue
                    onValueChange(newValue)
                }
            }
    ) {
        // Track
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.Gray, RoundedCornerShape(2.dp))
        )
// Thumb (Image)
        if (boxWidth.value > 0) {
            val offsetPx = value * boxWidth.value
            val offsetX = with(density) {
                (offsetPx - thumbSize.toPx() / 2).toInt()
            }

            Image(
                painter = thumbImage,
                contentDescription = "Thumb",
                modifier = Modifier
                    .size(thumbSize)
                    .offset { IntOffset(offsetX, 0) }
            )
        }

    }
}