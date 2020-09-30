package com.example.diplomapp.repo

import android.content.Context


import com.example.diplomapp.room.AppDatabase
import com.example.diplomapp.room.FlowerDAO
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent

class RoomRepo ( val context: Context) {

    @InstallIn(ApplicationComponent::class)
    @EntryPoint
    interface HiltProviderEntryPoint{
        fun appDatabase(): AppDatabase
        fun userDAO(): FlowerDAO?
    }

    private val appDatabase=getAppDatabase()
    private val userDAO=getUserDao()

    private fun getAppDatabase(): AppDatabase {
        val hiltEntryPoint= EntryPointAccessors.fromApplication(context,HiltProviderEntryPoint::class.java)
        return hiltEntryPoint.appDatabase()
    }
    private fun getUserDao(): FlowerDAO? {
        val hiltEntryPoint= EntryPointAccessors.fromApplication(context,HiltProviderEntryPoint::class.java)
        return hiltEntryPoint.userDAO()
    }
}