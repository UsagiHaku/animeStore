package com.example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.domain.OrderItem
import com.example.domain.User

@Database(entities = [OrderItem::class, User::class], version = 2, exportSchema = false)
abstract class AnimeStoreDatabase : RoomDatabase() {

    abstract fun orderItemDao(): OrderItemDao
    abstract fun userDao(): UserDao

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
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
