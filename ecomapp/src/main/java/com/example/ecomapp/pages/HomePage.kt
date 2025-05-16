package com.example.ecomapp.pages

import android.media.Image
import android.text.Layout.Alignment
import android.widget.SeekBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomapp.R
import com.example.ecomapp.signup.AuthViewModel
import com.example.ecomapp.utils.CommonSpacer
import com.example.ecomapp.utils.CustomImageSlider
import kotlinx.coroutines.launch

@Composable
fun HomePage(modifier: Modifier,
             authViewModel: AuthViewModel = viewModel()){

    var name by remember {
        mutableStateOf("")
    }
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    val lifecycleScope = LocalLifecycleOwner.current.lifecycleScope
    LaunchedEffect(Unit) {
        //why to use this to use coroutine scopes :-> compose is concept of creating UI
        // so we can not use any coroutine or any network
        // call or showing toast that is why in order to do all above we have to go for sideeffects like launchedEffect so that we can use show toast or coroutine etc...
        lifecycleScope.launch {
            name = authViewModel.getUserName().toString()
        }
    }
    Column (modifier = modifier.fillMaxSize()
        ) {
        Text(text = "Welcome Back",
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontSize = 30.sp,
            style = TextStyle(fontFamily = FontFamily.Cursive, fontWeight = FontWeight.ExtraBold)
        )
        CommonSpacer(10.dp)
        Text(text = name
            ,modifier = Modifier.fillMaxWidth().padding(10.dp),
            textAlign = TextAlign.Left,
            color = Color.Blue,
            fontSize = 20.sp,
            style = TextStyle(fontFamily = FontFamily.Cursive, fontWeight = FontWeight.Bold
            )
            )
        CommonSpacer(10.dp)
        //set banner  list here
        Card(modifier = modifier.fillMaxWidth().height(130.dp).padding(10.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = CardDefaults.elevatedShape
        ) {
            Image(painter = painterResource(R.drawable.ic_shopping),
                contentDescription = "",modifier = Modifier.padding(10.dp))

        }

    }

}