package com.example.ecomapp.Home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.example.ecomapp.pages.Cart
import com.example.ecomapp.pages.FavouritePage
import com.example.ecomapp.pages.HomePage
import com.example.ecomapp.pages.Profile

@Composable
fun HomeScreen(modifier: Modifier, navController: NavHostController) {
    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Cart",Icons.Default.ShoppingCart),
        NavItem("Favourite",Icons.Default.Favorite),
        NavItem("Profile",Icons.Default.AccountCircle)
    )
    var isSelectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold (
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed{index,navItem ->
                    NavigationBarItem(selected = index==isSelectedIndex,
                        onClick = {
                            isSelectedIndex = index
                        },
                        icon = {
                            Icon(imageVector = navItem.icon,
                                contentDescription = navItem.label)
                    })
                }

            }
        }
    ){
        ContentScreen(modifier = modifier.padding(it),isSelectedIndex)
    }

}


@Composable
fun ContentScreen(modifier: Modifier, isSelectedIndex: Int){
    when(isSelectedIndex){
        0->{
            HomePage(modifier)
        }
        1->{
            FavouritePage((modifier))
        }
        2->{
            Cart(modifier)
        }
        3->{
            Profile(modifier)
        }

    }

}

data class NavItem(val label:String, val icon: ImageVector)