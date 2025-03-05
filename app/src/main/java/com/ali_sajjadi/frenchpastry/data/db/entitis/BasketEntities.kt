package com.ali_sajjadi.frenchpastry.data.db.entitis

import androidx.room.ColumnInfo
import androidx.room.DeleteTable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ali_sajjadi.frenchpastry.data.DBHandler

@Entity(tableName = DBHandler.BASKET_TABLE)
data class BasketEntities(
   @PrimaryKey val id:Int,
   @ColumnInfo val title:String,
   @ColumnInfo val image:String,
   @ColumnInfo val price:String,
   @ColumnInfo val salePrice: Int,
   @ColumnInfo val weight: Int
)
