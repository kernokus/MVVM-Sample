package com.example.diplomapp.room

import androidx.room.*


@Dao
interface FlowerDAO {
    @Insert
    suspend fun insert(Flower:Flower?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(ListFlowers: List<Flower>?)

    @Update
    suspend fun update(Flower: Flower?)

    @Delete
    suspend fun delete(Flower: Flower?)

    @Query("SELECT * FROM Flower")
    suspend fun findAll(): List<Flower>
}