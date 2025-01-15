package com.test.begin1.roomdatabase.repository

import android.icu.text.CaseMap.Title
import com.test.begin1.roomdatabase.CardDao
import com.test.begin1.roomdatabase.CartDao
import com.test.begin1.roomdatabase.Entity.Card
import com.test.begin1.roomdatabase.Entity.Cart
import kotlinx.coroutines.flow.Flow

class Repository(
    private val cardDao: CardDao,
    private val cartDao: CartDao
) {

    suspend fun deletecard(id: Int) {
        cardDao.deleteCard(id)
    }


    //shopping card

    var getallcards = cardDao.getallCards()

    suspend fun insertCard(card: Card) {
        cardDao.addCard(card)
    }

    fun searchByTitle(title: String): Flow<List<Card>> {
        return cardDao.searchbytitle(title)
    }



    //cart

    var getallcarts = cartDao.getallitems()

    suspend fun insertCart(cart: Cart) {
        cartDao.addOrUpdateItem(cart)
    }

    suspend fun deletecart(id: Int) {
        cartDao.deleteitem(id)
    }

}