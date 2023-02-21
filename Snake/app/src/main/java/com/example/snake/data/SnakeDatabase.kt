package com.example.snake.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 2)
abstract class SnakeDatabase: RoomDatabase() {
    abstract fun playerScoreDao(): PlayerScoreDao

    companion object {
        fun getDatabase(applicationContext: Context): SnakeDatabase {
            return Room.databaseBuilder(
                applicationContext,
                SnakeDatabase::class.java,
                "snake",
            ).fallbackToDestructiveMigration().build()
        }
    }
}