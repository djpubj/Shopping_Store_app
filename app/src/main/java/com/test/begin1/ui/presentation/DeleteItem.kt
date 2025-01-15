package com.test.begin1.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.test.begin1.ui.viewmodels.DeleteItemViewmodel


@Composable
fun DeleteItem() {
    val viewModel = viewModel(modelClass = DeleteItemViewmodel::class.java)
    val cards by viewModel.allCards.collectAsState(initial = emptyList())

    Column {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF42A5F5))
                .padding(16.dp)
        ) {
            Text(
                text = "DeleteItem",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF1565C0)
            )
        }

        LazyColumn(state = rememberLazyListState()) {
            items(cards) { card ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // Space between elements
                ) {
                    Text(
                        text = card.title,
                        modifier = Modifier.weight(1f), // Allows the text to take up the remaining space
                        style = MaterialTheme.typography.bodyLarge// Use Material Theme typography for consistency
                    )
                    Text(
                        text = "\u20B9 " + (card.price.toString()),
                        modifier = Modifier.weight(1f), // Allows the text to take up the remaining space
                        style = MaterialTheme.typography.bodyLarge// Use Material Theme typography for consistency
                    )
                    Button(
                        onClick = { viewModel.deleteCardItem(card.id) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Delete",
                            color = MaterialTheme.colorScheme.onPrimary // Ensures contrast
                        )
                    }
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black))
            }
        }
    }
}