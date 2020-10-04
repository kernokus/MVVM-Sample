package com.example.diplomapp.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Flower::class], version = 1,exportSchema = false)
abstract class AppDatabase:RoomDatabase(){
    abstract fun flowerDao(): FlowerDAO?
}