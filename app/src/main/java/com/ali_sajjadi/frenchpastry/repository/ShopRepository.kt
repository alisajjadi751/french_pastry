package com.ali_sajjadi.frenchpastry.repository

import com.ali_sajjadi.frenchpastry.data.DBHandler
import com.ali_sajjadi.frenchpastry.data.db.entitis.BasketEntities
import kotlinx.coroutines.flow.Flow

class ShopRepository(
   private val dbHandler: DBHandler
) {

    private val basketDAO = dbHandler.basketDao()

    suspend fun insertProduct(basketEntities: BasketEntities){
        basketDAO.insert(basketEntities)
    }

    fun getBasketProducts(): Flow<List<BasketEntities>> {
        return basketDAO.getBasket()
    }

    suspend fun deleteProducts(basketEntities: BasketEntities){
        basketDAO.delete(basketEntities)
    }

}