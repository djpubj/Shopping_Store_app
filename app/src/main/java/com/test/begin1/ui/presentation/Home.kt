package com.test.begin1.ui.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.begin1.ui.component.CardItem
import com.test.begin1.ui.component.Searchitem
import com.test.begin1.ui.viewmodels.HomeViewmodel

@Composable
fun Home() {
    val viewModel = viewModel(modelClass = HomeViewmodel::class.java)
    val searchResults by viewModel.searchResults.collectAsState(emptyList())
    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var isSearching by remember { mutableStateOf(false) }
    val cards by viewModel.allCards.collectAsState(initial = emptyList())

    var showbuttoncross by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
    ) {
        // Search Bar
        Column(
            modifier = Modifier
                .background(Color(0xFF42A5F5))
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Shopping Store",
                modifier = Modifier.padding(bottom = 16.dp),
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF1565C0)
            )
            Row {
                TextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        if (searchText.text.isNotEmpty()) {
                            showbuttoncross=true
                            isSearching = true
                            viewModel.searchByTitle(searchText.text)
                        } else {
                            showbuttoncross=false
                            isSearching = false
                        }
                    },
                    placeholder = { Text(text = "Search") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                        )
                    },
                    trailingIcon = {

                    AnimatedVisibility(showbuttoncross) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp)) // Apply rounded corners to the clickable area
                                    .clickable(
                                        indication = rememberRipple(bounded = false), // Ripple effect for the clickable area
                                        interactionSource = remember { MutableInteractionSource() }
                                    ) {
                                        showbuttoncross = false
                                        isSearching = false
                                        searchText = TextFieldValue("")

                                    }
                                    .padding(12.dp) // Provide padding for a larger clickable area
                                    .height(35.dp) // Ensure the clickable area matches the height of the TextField
                                    .fillMaxHeight() // Make sure it fills the available height
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear Icon",
                                    modifier = Modifier
                                        .align(Alignment.Center) // Center the icon in the clickable area
                                )
                            }
                        }
                    },
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Search Results or Content
        if (isSearching) {
            searchResults?.let { cardList ->
                Text(
                    text = "Search Results:",
                    modifier = Modifier.padding(bottom = 8.dp),
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(cardList) { card ->
                        Searchitem(card = card, viewModel = viewModel)
                    }
                }
            } ?: Text(
                text = if (searchText.text.isNotEmpty()) "No results found." else "",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        } else {
            // LazyRow and LazyVerticalGrid
            LazyVerticalGrid(
                state = rememberLazyGridState(),
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f), // Let the grid take up remaining space
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
//                item(span = { GridItemSpan(maxCurrentLineSpan) }) {
//                    LazyRow(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(300.dp),
//                        horizontalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        items(cards) { card ->
//                            CardItem(card, viewModel)
//                        }
//                    }
//                }
                items(cards) { card ->
                    CardItem(card, viewModel)
                }
            }
        }
    }
}



