package com.test.begin1.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.test.begin1.navigation.ScreenB
import com.test.begin1.navigation.ScreenD

@Composable
fun Account(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF42A5F5))
                .padding(16.dp)
        ) {
            Text(
                text = "Account", style = MaterialTheme.typography.headlineLarge,color = Color(0xFF1565C0)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier
                    .align(Alignment.Start)
                    .fillMaxWidth()
                    .padding(vertical = 1.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE0F7FA), // Set the background color
                    contentColor = Color.Black // Optionally, set the content color (text color)
                ),
                onClick = { navController.navigate(ScreenB) }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start // Align the content (text) to the start (left)
                ) {
                    Text(
                        text = "Add Item by Admin only",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE0F7FA), // Set the background color
                    contentColor = Color.Black // Optionally, set the content color (text color)
                ),
                onClick = {navController.navigate(ScreenD)}

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start // Align the content (text) to the start (left)
                ) { Text(text = "Delete Items by Admin only", style = MaterialTheme.typography.bodyLarge) }
            }
        }
    }
}