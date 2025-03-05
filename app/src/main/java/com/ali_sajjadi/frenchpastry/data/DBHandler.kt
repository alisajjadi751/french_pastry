package com.ali_sajjadi.frenchpastry.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ali_sajjadi.frenchpastry.data.db.dao.BasketDAO
import com.ali_sajjadi.frenchpastry.data.db.entitis.BasketEntities

@Database(
    entities = [BasketEntities::class],
    version = DBHandler.DATABASE_VERSION
)
abstract class DBHandler : RoomDatabase() {

    abstract fun basketDao(): BasketDAO

    companion object {

        const val DATABASE_NAME = "main_database"
        const val DATABASE_VERSION = 1

        const val BASKET_TABLE = "basket_table"

        private var INSTANCE: DBHandler? = null

        fun getDatabase(context: Context): DBHandler {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DBHandler::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}