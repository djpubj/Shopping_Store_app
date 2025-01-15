package com.test.begin1.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.test.begin1.dataclass.NavItem
import com.test.begin1.ui.presentation.Account
import com.test.begin1.ui.presentation.Cart
import com.test.begin1.ui.presentation.Home
import com.test.begin1.ui.viewmodels.CartViewmodel

@Composable
fun BottomNav(navController: NavController) {
    val viewModel = viewModel(modelClass = CartViewmodel::class.java)
    val cartlist by viewModel.allCarts.collectAsState(initial = emptyList())
    val cartbadage = cartlist.sumOf { it.quantity }


    val navItemList = remember {
        mutableStateListOf(
            NavItem("home", Icons.Default.Home),
            NavItem("Account", Icons.Default.AccountCircle),
            NavItem("Cart", Icons.Default.ShoppingCart)
        )
    }

    var selecteditem by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier,
                containerColor = Color(0xFF42A5F5),
                contentColor = Color.LightGray
            ) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selecteditem == index,
                        onClick = {
                            selecteditem = index
                        },
                        icon = {
                            BadgedBox(badge = {
                                if (navItem.name == "Cart" && cartbadage > 0) {
                                    Badge {
                                        Text(text = cartbadage.toString())
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = navItem.icon,
                                    contentDescription = navItem.name,
                                    tint = Color.White
                                )
                            }
                        },
                        label = { Text(text = navItem.name, color = Color.White) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavScreen(
            modifier = Modifier.padding(paddingValues),
            selecteditem = selecteditem,
            navController = navController
        )
    }
}

@Composable
fun NavScreen(modifier: Modifier, selecteditem: Int, navController: NavController) {
    when (selecteditem) {
        0 -> Home()
        1 -> Account(navController)
        2 -> Cart()
    }
}