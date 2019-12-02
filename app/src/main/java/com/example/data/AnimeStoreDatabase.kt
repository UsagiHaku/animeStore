package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.OrderItem

@Database(entities = arrayOf(OrderItem::class), version = 1, exportSchema = false)
abstract class AnimeStoreDatabase : RoomDatabase() {

    abstract fun orderItemDao(): OrderItemDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AnimeStoreDatabase? = null

        fun getDatabase(context: Context): AnimeStoreDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimeStoreDatabase::class.java,
                    "anime_store_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
