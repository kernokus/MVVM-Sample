package com.example.diplomapp.repo

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.diplomapp.api.FlowService


import com.example.diplomapp.room.AppDatabase
import com.example.diplomapp.room.Flower
import com.example.diplomapp.room.FlowerDAO
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlowerRepo (private val context: Context) {
    companion object {
        const val API_KEY_PIXABAY = "17951668-b5172637b18686031bb7db33b"
    }

    @InstallIn(ApplicationComponent::class)
    @EntryPoint
    interface HiltProviderEntryPoint {
        fun flowerDAO(): FlowerDAO?
        fun network(): FlowService
    }

    private val flowerDao = getUserDao()
    private val network = getNetwork()

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

    suspend fun loadInDD(): List<Flower>? {
        val colorListFromDB=flowerDao?.findAll() //беру данные из базы данных,если они не null
        val data: MutableList<Flower>? = arrayListOf()
        if (colorListFromDB?.size!=0 && colorListFromDB!=null) { //если бд не пустая
            Log.d("InLOADING",colorListFromDB.toString())
            return colorListFromDB //возвращаю из бд
        }
        else {
            val colorListFromNetwork = network.getFlowList(API_KEY_PIXABAY, "yellow+flowers", "photo").colorList
            if (colorListFromNetwork != null) {
                for (item in colorListFromNetwork) { //преобразую для хранения в room
                    val newFlower = Flower(
                        0,
                        item.downloads.toString()+" downloads",
                        "Tags: "+item.tags.toString(),
                        item.webformatURL.toString()
                    )
                    data?.add(newFlower)
                }
                flowerDao?.saveAll(data)
            }
            return data //возвращаю только что полученные из бд данные
        }
        }
    }
