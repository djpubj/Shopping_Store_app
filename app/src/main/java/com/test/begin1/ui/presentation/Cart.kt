package com.test.begin1.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.begin1.roomdatabase.Entity.Cart
import com.test.begin1.ui.viewmodels.CartViewmodel

@Composable
fun Cart() {

    val viewModel = viewModel(modelClass = CartViewmodel::class.java)
    val cartlist by viewModel.allCarts.collectAsState(initial = emptyList())


    Box(modifier = Modifier.padding(bottom = 100.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF42A5F5))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your Cart",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color(0xFF1565C0)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Default.ShoppingCart, // Default cart icon
                    contentDescription = "Cart Icon"
                )
            }
            // Calculate total price
            val totalPrice = cartlist.sumOf { it.price * it.quantity }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Cart Items List
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(cartlist) { cart ->
                        CartItemRow(cartItem = cart, onRemove = {
                            viewModel.deleteCartItem(cart.id)
                        })
                    }

                }

                // Total Price
                Text(
                    text = "Total: \u20B9${"%.2f".format(totalPrice)}",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
                Button(
                    onClick = {
                        // Handle checkout logic
//                    navController.navigate("CheckoutScreen")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(Color(0xFFF44336))
                ) {
                    Text(text = "Checkout")
                }

                // Checkout Button

            }
        }

    }
}

@Composable
fun CartItemRow(cartItem: Cart, onRemove: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFE0F7FA), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Item Name
        Text(
            text = cartItem.name,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )

        // Quantity
        Text(
            text = "${cartItem.quantity} x ", style = MaterialTheme.typography.bodyLarge
        )

        // Item Price
        Text(
            text = "\u20B9${"%.2f".format(cartItem.price * cartItem.quantity)}",
            style = MaterialTheme.typography.bodyLarge
        )

        // Remove Button
        IconButton(onClick = onRemove) {
            Icon(
                imageVector = Icons.Default.Delete, contentDescription = "Remove Item"
            )
        }
    }
}


