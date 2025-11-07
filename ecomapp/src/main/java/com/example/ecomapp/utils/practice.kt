package com.example.ecomapp.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.ecomapp.Home.ContentScreen
import com.example.ecomapp.Home.NavItem
import com.example.ecomapp.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun practice() {
    // scaffold with bottom navigation bar

    val navlist = listOf(
        NavItem(label = "test1", icon = Icons.Default.Home),
        NavItem(label = "test1", icon = Icons.Default.Favorite)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                navlist.forEachIndexed { index, item ->
                    NavigationBarItem(selected = true, modifier = Modifier, onClick = {

                    }, icon = {
                        BadgedBox(badge = {
                            Badge() {
                                Text(text = "text")
                            }
                        }) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label
                            )

                        }
                    })


                }

            }
        }
    ) {
        ContentScreen(modifier = Modifier.padding(10.dp), isSelectedIndex = 1)
    }

}


@Composable
fun practisehome() {

    val navlist = listOf(
        NavItem(label = "label1", icon = Icons.Default.Home),
        NavItem(label = "label2", icon = Icons.Default.Favorite),
    )


    Scaffold(
        bottomBar = {
            NavigationBar {
                navlist.forEachIndexed { index, item ->
                    NavigationBarItem(selected = true, onClick = {

                    }, icon = {
                        BadgedBox(badge = {
                            Badge() {
                                Text(text = "9")
                            }
                        }) {
//                            Icon(painterResource(R.drawable.ic_signup), contentDescription = "")
                            Icon(imageVector = item.icon, contentDescription = item.label)
                        }
                    })
                }

            }
        }
    ) { it ->
        Text(text = "ddfsd", modifier = Modifier.padding(it))
    }

}

@Composable
fun checkScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Red)
    ) {

    }
}

@Composable
fun textiniRow() {
    val rowmodifier = Modifier.fillMaxWidth()
    Row(
        modifier = rowmodifier.padding(top = 40.dp)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "orderItem.orderStatus",
            style = TextStyle(
                fontWeight = FontWeight.Bold, color = colorResource(
                    R.color.grdient_green3
                )
            )
        )
        Text(
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = "dateToString",
            textAlign = TextAlign.End,
            style = TextStyle(
                fontWeight = FontWeight.Bold, color = colorResource(
                    R.color.grdient_green3
                )
            )
        )
    }
}

//Scaffold (
//bottomBar = {
//    NavigationBar {
//        navItemList.forEachIndexed{index,navItem ->
//            NavigationBarItem(selected = index==isSelectedIndex,
//                onClick = {
//                    isSelectedIndex = index
//                },
//                icon = {
//                    BadgedBox( badge = {
//                        Badge(){
//                            Text(text =  prefBadgeCount.toString())
//                        }
//                    }
//                    ) {
//                        Icon(imageVector = navItem.icon,
//                            contentDescription = navItem.label)
//                    }
//
//                })
//        }
//
//    }
//}
//){
//    ContentScreen(modifier = modifier.padding(it),isSelectedIndex)
//}