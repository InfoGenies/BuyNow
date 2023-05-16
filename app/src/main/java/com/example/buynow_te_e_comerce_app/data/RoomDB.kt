package com.example.buynow_te_e_comerce_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class,FavorEntity::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun favDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getInstance(context: Context): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(RoomDB::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "app_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }

}