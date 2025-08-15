package com.example.ecomapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.ecomapp.ui.theme.Android2oTheme
import com.example.ecomapp.utils.PaymentListener
import com.example.ecomapp.utils.ShowToast
import com.example.ecomapp.utils.addOrderAndRemoveCart
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity(),PaymentListener {
    // Keep state in activity so both Compose and the interface can access it
    private var _showDialog = mutableStateOf(false)
    private var dialogTriggered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        setContent {
            Android2oTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets.statusBars // space around screen
                ) { innerPadding ->
                    AppNavigation(Modifier
                        .padding(innerPadding)
                        .background(colorResource(R.color.grdient_green3)),
                        context= this@MainActivity,
                        _showDialog =_showDialog)
                }
                // The dialog belongs to MainActivity's content
                if (dialogTriggered && _showDialog.value) {
                    AlertDialog(
                        onDismissRequest = { _showDialog.value = false },
                        title = { Text("Order") },
                        text = { Text("Payment Success") },
                        confirmButton = {
                            TextButton(onClick = { _showDialog.value = false
                                addOrderAndRemoveCart()
                                GlobalNavigator.navController.popBackStack()
                                GlobalNavigator.navController.navigate(HomeRoute)
                                _showDialog.value = false
                                dialogTriggered = false
                            }) {
                                Text("OK")
                            }
                        }
                    )
                } else if(!_showDialog.value && dialogTriggered){
                    AlertDialog(
                        onDismissRequest = { _showDialog.value = false },
                        title = { Text("Order") },
                        text = { Text("Payment Fail") },
                        confirmButton = {
                            TextButton(onClick = { _showDialog.value = false

                            }) {
                                Text("OK")
                            }
                        }
                    )
                }
            }

        }
    }

    override fun paymentSuccess() {
        _showDialog.value = true
        dialogTriggered = true

    }

    override fun paymentFailure() {
        _showDialog.value = false
        dialogTriggered = true

    }
}
