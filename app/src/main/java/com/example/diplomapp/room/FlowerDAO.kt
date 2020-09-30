package com.example.diplomapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update


@Dao
interface FlowerDAO {
    @Insert
    suspend fun insert(User:Flower?)

    @Update
    suspend fun update(User: Flower?)

    @Delete
    suspend fun delete(User: Flower?)
}