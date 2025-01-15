package com.test.begin1.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.begin1.roomdatabase.Entity.Card
import com.test.begin1.roomdatabase.Graph
import com.test.begin1.roomdatabase.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DeleteItemViewmodel(
    private val repository: Repository = Graph.repository
) : ViewModel() {

    // Observing all cards and cart items
    val allCards: Flow<List<Card>> = repository.getallcards

    fun deleteCardItem(id: Int) {
        viewModelScope.launch {
            repository.deletecard(id)
        }
    }
}