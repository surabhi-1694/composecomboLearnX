package com.example.bottomnavigationbar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(dataModel: newsDataModel, modifier: Modifier) {
    val bottomNavController = rememberNavController()
    var selected by remember {
        mutableIntStateOf(0)
    }

    val navItemList = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Notification", Icons.Default.Notifications),
        NavItem("Settings", Icons.Default.Settings)
    )

    //we are using scaffold so that we can add bottom navigation , toolbar within the page :-)
    Scaffold(modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                navController = bottomNavController,
                navItemList = navItemList,
                selected = selected, //  Pass selected
                onItemSelected = { index -> selected = index
                    when (index) {
                        0 -> bottomNavController.navigate(ScreenHome) // Home
                        1 -> bottomNavController.navigate(ScreenNotification) // Notification
                        2 -> bottomNavController.navigate(ScreenSetting) // Settings
                    }
                } //  Update state
            )
        }) { innerPadding ->

//        ContentScreen(navController,dataModel,
//            modifier = Modifier.padding(innerPadding), selected)

        NavHost(
            navController = bottomNavController,
            startDestination = ScreenHome,
            Modifier.padding(innerPadding)
        ) {
            composable<ScreenHome> {
                HomeScreen(bottomNavController,dataModel) // Pass main navController
            }
            composable<ScreenNotification> {
                NotificationScreen()
            }
            composable<ScreenSetting> {
                SettingScreen()
            }
            composable<ScreenList> {
                val args = it.toRoute<ScreenList>()
                HomeListScreen(args)
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
//fun BottomNavigationBar(navController: NavController, navItemList: List<NavItem>,selected: Int) {
    fun BottomNavigationBar(
        navController: NavController,
        navItemList: List<NavItem>,
        selected: Int, // Receive selected index
        onItemSelected: (Int) -> Unit // Receive callback to update selected
    ) {
    NavigationBar {
        navItemList.forEachIndexed { index, navItem ->
            NavigationBarItem(selected = selected == index,
                onClick = {
//                    navController.navigate(navItem)
                    onItemSelected(index)
                },
                icon = {
                    BadgedBox(badge = {
                        Badge(){
                            Text(text = "5")
                        }
                    }){
                        Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                    }

                },
                label = {
                    Text(
                        text = navItem.label, fontSize = 12.sp, fontStyle = FontStyle.Italic
                    )

                }
            )
        }
    }
}

@Composable
fun ContentScreen(navController: NavController, dataModel: newsDataModel, modifier: Modifier, selected: Int) {
    when (selected) {
        0 -> HomeScreen(navController =navController ,dataModel)
        1 -> NotificationScreen()
        2 -> SettingScreen()
    }

}

