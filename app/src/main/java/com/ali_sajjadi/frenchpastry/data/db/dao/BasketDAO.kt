package com.ali_sajjadi.frenchpastry.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ali_sajjadi.frenchpastry.data.DBHandler
import com.ali_sajjadi.frenchpastry.data.db.entitis.BasketEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDAO {

    @Insert
    suspend fun insert(basketEntities: BasketEntities)

    @Delete
    suspend fun delete(basketEntities: BasketEntities)

    @Query("SELECT * FROM ${DBHandler.BASKET_TABLE}")
    fun getBasket():Flow<List<BasketEntities>>

}