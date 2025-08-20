package com.example.ecomapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecomapp.AuthRoute
import com.example.ecomapp.GlobalNavigator
import com.example.ecomapp.HomeRoute
import com.example.ecomapp.OrderRoute
import com.example.ecomapp.R
import com.example.ecomapp.signup.User
import com.example.ecomapp.utils.CommonHorizontalSpacer
import com.example.ecomapp.utils.getUserDocument
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun Profile(modifier: Modifier) {
    val usermodel = remember {
        mutableStateOf(User())
    }

    LaunchedEffect(Unit) {
        val userDoc = getUserDocument()
        userDoc.get().addOnCompleteListener { userTask->
           val userResult =  userTask.result.toObject(User::class.java)
            if (userResult!=null){
                usermodel.value = userResult
            }
        }
    }
    Column(modifier = modifier.fillMaxSize().padding(10.dp)) {
        //name ,address,profile image, order history, payment history.
        Text(text= "Profile page", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp))

        Image(modifier = Modifier.fillMaxWidth().size(200.dp), painter = painterResource(R.drawable.outline_face_2_24),
            contentDescription = "Profile picture")
        Text(text = "Name :", textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)

        Text(text = usermodel.value.name, textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp)
        CommonHorizontalSpacer(10.dp)
        Text(text = "Address: ", textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)
        Text(text = usermodel.value.address, textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp)

        CommonHorizontalSpacer(10.dp)

        Button(onClick = {
            GlobalNavigator.navController.navigate(OrderRoute)
        },modifier = Modifier.fillMaxWidth()) {
            Text(text= "View My Orders: ")
        }
        CommonHorizontalSpacer(10.dp)

        Button(onClick = {
            Firebase.auth.signOut()
            GlobalNavigator.navController.navigate(AuthRoute){ // name of screen where you want to navigate
                popUpTo<HomeRoute>{ // name of the screen which you wan to remove from back stack ; probably the previous one
                    inclusive = true
                }
            }
        },modifier = Modifier.fillMaxWidth()) {
            Text(text =" Logout")
        }

    }
}