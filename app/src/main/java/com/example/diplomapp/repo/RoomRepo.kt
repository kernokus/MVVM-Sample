package com.example.diplomapp.repo

import android.content.Context
import com.example.diplomapp.api.FlowService


import com.example.diplomapp.room.AppDatabase
import com.example.diplomapp.room.Flower
import com.example.diplomapp.room.FlowerDAO
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent

class RoomRepo ( val context: Context) {
    companion object {
        const val API_KEY_PIXABAY = "17951668-b5172637b18686031bb7db33b"
    }

    @InstallIn(ApplicationComponent::class)
    @EntryPoint
    interface HiltProviderEntryPoint {
        fun appDatabase(): AppDatabase
        fun flowerDAO(): FlowerDAO?
        fun network(): FlowService
    }

    private val appDatabase = getAppDatabase()
    private val flowerDao = getUserDao()
    private val network = getNetwork()

    private fun getAppDatabase(): AppDatabase {
        val hiltEntryPoint =
            EntryPointAccessors.fromApplication(context, HiltProviderEntryPoint::class.java)
        return hiltEntryPoint.appDatabase()
    }

    private fun getUserDao(): FlowerDAO? {
        val hiltEntryPoint =
            EntryPointAccessors.fromApplication(context, HiltProviderEntryPoint::class.java)
        return hiltEntryPoint.flowerDAO()
    }

    private fun getNetwork(): FlowService {
        val hiltEntryPoint =
            EntryPointAccessors.fromApplication(context, HiltProviderEntryPoint::class.java)
        return hiltEntryPoint.network()
    }


    suspend fun loadInDD() {
        val data: MutableList<Flower>? = arrayListOf()
        val dataNetwork = network.getFlowList(API_KEY_PIXABAY, "yellow+flowers", "photo").hits
        if (dataNetwork != null) {
            for (item in dataNetwork) {
                val newFlower = Flower(
                    0,
                    item.downloads.toString(),
                    item.tags.toString(),
                    item.pageURL.toString()
                )
                data?.add(newFlower)
            }
            flowerDao?.saveAll(data)
        }

    }
}