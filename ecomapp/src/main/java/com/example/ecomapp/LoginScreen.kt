package com.example.ecomapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.ecomapp.signup.AuthViewModel
import com.example.ecomapp.utils.CommonButton
import com.example.ecomapp.utils.CommonHorizontalSpacer
import com.example.ecomapp.utils.CommonVericalSpacer
import com.example.ecomapp.utils.DifferentScreenConfig
import com.example.ecomapp.utils.ShowToast
import com.example.ecomapp.utils.commonLoginField
import com.example.ecomapp.utils.commonLoginHeader
import com.example.ecomapp.utils.isEmailValid
import com.example.ecomapp.utils.isPwdError
import com.example.ecomapp.utils.windowSizeClass.fromWindowSizeClass


//common login pwd  = Admin@1234
@Composable
fun LoginScreen(modifier: Modifier,
                navController: NavHostController,
                authViewModel: AuthViewModel = viewModel()) {
    var context = LocalContext.current

    val rootmodifier = modifier
        .fillMaxSize()
        .padding( vertical = 24.dp)
        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
        .background(Color.White)
        .consumeWindowInsets(WindowInsets.navigationBars)

    val windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val fromWindowSizeClass = fromWindowSizeClass(windowSizeClass)
    when(fromWindowSizeClass){
        DifferentScreenConfig.MOBILE_PORTRAIT->{
            Column(
                modifier = rootmodifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //check for potrait /landscape / tablet
                commonLoginHeader(modifier = Modifier)
                commonLoginField(modifier = Modifier.fillMaxWidth() ,
                    authViewModel = authViewModel,navController = navController
                    ,context = context)
            }
        }
        DifferentScreenConfig.MOBILE_LANDSCAPE->{
            Row(
                modifier = rootmodifier
                    .padding(10.dp)
                    .windowInsetsPadding(WindowInsets.displayCutout)
            ) {
                //check for potrait /landscape / tablet
                commonLoginHeader(Modifier.weight(1f))
                commonLoginField(modifier = Modifier.fillMaxWidth().weight(1f).verticalScroll(
                    rememberScrollState()
                ) ,
                    authViewModel = authViewModel,navController = navController
                    ,context = context)
            }
        }

        DifferentScreenConfig.TABLET_PORTRAIT,DifferentScreenConfig.TABLET_LANDSCAPE,DifferentScreenConfig.DESKTOP->{

        }


    }

}

@Composable
fun disply(s: String) {
    Text(text = "s")
}
