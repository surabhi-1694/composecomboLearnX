package com.example.emoposexmlmigration.composefuns

import android.content.Context
import android.health.connect.datatypes.units.Length
import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.emoposexmlmigration.R
import com.example.emoposexmlmigration.utils.CommonButton
import com.example.emoposexmlmigration.utils.CommonVericalSpacer

//android view for xml view
@Composable
fun ComposeMigrationUI() {
    val context = LocalContext.current.applicationContext

    var clickCount by remember {
        mutableIntStateOf(0)
    }

    var isWebViewShow by remember {
        mutableStateOf(false)
    }
    Column {
        CommonButton(isEnable = false, text = "Compose Button", onClick = {
            Toast.makeText(context,"Compose Button",Toast.LENGTH_LONG).show()

        })
        CommonVericalSpacer(5.dp)
        Button(onClick = {
            isWebViewShow = true

        }, modifier = Modifier.fillMaxWidth()){
            Text("WEBVIEW COMPOSE BUTTON ")
        }
        /*
        *
        * there are few ways to add xml view in compose for ex,
        *  using androidview with modifier so that we can maintain padding and margins
        * Or if we have large view to settle than we can go for customview which we create using attribut, canvas etc..
        *  */
        // widget.TextView
        AndroidView( factory = { ctx ->
            TextView(ctx).apply {
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                text = "You have clicked the buttons: $clickCount" + " times"
                Log.e("Txtview","Original view")
            }
        },
            // update will call when state changes as in compose ui updates when any state changes
            // same in Android view update method call when state change so we can update UI related changes
            update = {
            // Update TextView with the current state value
            it.text = "You have clicked the buttons: $clickCount" + " times"
            Log.e("Txtview","Update view")
        })
            AndroidView(
                /*
                * here we are using wrapContentSize()
                * so that image view can settle if we use fill size than we will not be able to witness changes in update method
                *
                * */
                modifier = Modifier.wrapContentSize(),
                factory = { context ->
//                MyView(context)
                    ImageView(context).apply {
                        Log.e("Imageview_Click","Original Click")
                        val drawable = ContextCompat.getDrawable(context, R.drawable.baseline_hexagon_24)
                        setImageDrawable(drawable)
                        layoutParams = ViewGroup.LayoutParams(200,200)
                        setOnClickListener {
                            Log.e("Imageview_Click","Click")
                            clickCount ++
                            /*
                            * onclick we will update clickCount to update imageview size
                            *  and to check update
                            * here we also open webview which is used by Androidview
                            *
                            * */


                        }
                    }
                },
                update = { view ->
                    if(clickCount > 0){
                        Log.e("Imageview_Click","update")
                        view.layoutParams = ViewGroup.LayoutParams(20+clickCount,20+clickCount)
                        view.requestLayout()
                    }
                },
                onReset = { view ->
                    Log.e("Imageview_Click","onReset")
                }
            )
        if(isWebViewShow){
            webView()
        }

    }
}

@Composable
fun webView(){
    val mUrl = "https://www.google.com"
    // Adding a WebView inside AndroidView with layout as full screen
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}

