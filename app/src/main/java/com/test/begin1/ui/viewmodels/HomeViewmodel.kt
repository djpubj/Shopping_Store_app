package com.test.begin1.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.begin1.roomdatabase.Entity.Card
import com.test.begin1.roomdatabase.Entity.Cart
import com.test.begin1.roomdatabase.Graph
import com.test.begin1.roomdatabase.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


//@OptIn(FlowPreview::class)
class HomeViewmodel(
    private val repository: Repository = Graph.repository
) : ViewModel() {

    // Observing all cards and cart items
    val allCards: Flow<List<Card>> = repository.getallcards

    // Search result for a card by title
    private val _searchResults = MutableStateFlow<List<Card>>(emptyList())
    val searchResults: StateFlow<List<Card>> = _searchResults.asStateFlow()

    fun searchByTitle(title: String) {
        viewModelScope.launch {
            repository.searchByTitle(title)
                .collect { cards ->
                    _searchResults.value = cards
                }
        }
    }

    // Function to add or update a cart item
    fun addCart(cart: Cart) {
        viewModelScope.launch {
            repository.insertCart(cart)
        }
    }

}