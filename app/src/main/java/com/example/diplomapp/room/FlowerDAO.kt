package com.example.diplomapp.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.diplomapp.model.FlowerListPOJO
import com.example.diplomapp.model.FlowerPOJO


@Dao
interface FlowerDAO {
    @Insert
    suspend fun insert(Flower:Flower?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(ListFlowers: List<Flower>?)

    @Update
    suspend fun update(Flower: Flower?)

    @Delete
    suspend fun delete(Flower: Flower?)

    @Query("SELECT * FROM Flower")
    fun findAll(): LiveData<List<Flower>>
}