package com.example.emoposexmlmigration.composefuns

import android.content.Context
import android.health.connect.datatypes.units.Length
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Column {
        CommonButton(isEnable = false, text = "Compose Button", onClick = {
            Toast.makeText(context,"Compose Button",Toast.LENGTH_LONG).show()
        })
        CommonVericalSpacer(5.dp)

        /*
        *
        * there are few ways to add xml view in compose for ex,
        *  using androidview with modifier so that we can maintain padding and margins
        * Or if we have large view to settle than we can go for customview which we create using attribut, canvas etc..
        *  */
        AndroidView(
            modifier = Modifier.size(90.dp),
            // Occupy the max size in the Compose UI tree
            factory = { context ->
//                MyView(context)
                ImageView(context).apply {
                    val drawable = ContextCompat.getDrawable(context, R.drawable.baseline_hexagon_24)
                    setImageDrawable(drawable)
                    setOnClickListener {

                    }
                }
            },
            update = { view ->
                view.layoutParams = ViewGroup.LayoutParams(40,40)
            },
            onReset = { view ->
            }
        )    }



}

