package com.example.snake.data

import androidx.room.*

@Dao
interface PlayerScoreDao {
    @Query("SELECT * FROM player")
    fun getAll(): List<Player>

    @Insert
    fun insert(player: Player): Long

    @Update
    fun update(player: Player)

    @Delete
    fun deleteItem(player: Player)
}

