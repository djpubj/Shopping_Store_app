package com.test.begin1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.begin1.ui.presentation.AddItem
import com.test.begin1.ui.presentation.DeleteItem

@Composable
fun PageNavigation(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenA, builder = {
        composable<ScreenA> {
            BottomNav(navController)
        }

        composable<ScreenB> {
            AddItem(navController)
        }

        composable<ScreenC> {
            BottomNav(navController)
        }
        composable<ScreenD> {
            DeleteItem()
        }

    })

}