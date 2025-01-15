package com.test.begin1.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.begin1.roomdatabase.Entity.Card
import com.test.begin1.roomdatabase.Graph
import com.test.begin1.roomdatabase.repository.Repository
import kotlinx.coroutines.launch


class AddItemViewmodel(
    private val repository: Repository = Graph.repository
) : ViewModel() {

    // Function to insert a card
    fun addCard(card: Card) {
        viewModelScope.launch {
            repository.insertCard(card)
        }
    }


}