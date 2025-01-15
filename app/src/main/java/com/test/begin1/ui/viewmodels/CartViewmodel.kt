package com.test.begin1.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.begin1.roomdatabase.Entity.Cart
import com.test.begin1.roomdatabase.Graph
import com.test.begin1.roomdatabase.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CartViewmodel(private val repository: Repository = Graph.repository) : ViewModel() {
    val allCarts: Flow<List<Cart>> = repository.getallcarts

    // Function to delete a cart item by ID
    fun deleteCartItem(id: Int) {
        viewModelScope.launch {
            repository.deletecart(id)
        }
    }

}