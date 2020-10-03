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


//    suspend fun loadfromBD(): MutableLiveData<List<Flower>> {
//        // withContext(Dispatchers.IO){}
//        val temp=flowerDao?.findAll() //беру данные из базы данных,если они не null
//        val data: MutableList<Flower>? = arrayListOf()
//        val liveData:MutableLiveData<List<Flower>> = MutableLiveData()
//            liveData.postValue(temp)
//            Log.d("InLOADING",temp.toString())
//            return liveData //возвращаю из бд
//    }

//    suspend fun isNotDBEmpty():Boolean { //db- not empty?
//        if (flowerDao?.findAll()!=null) {
//            return true
//        }
//        return false
//    }

    suspend fun loadInDD(): List<Flower>? {
        val temp=flowerDao?.findAll() //беру данные из базы данных,если они не null
        val data: MutableList<Flower>? = arrayListOf()
        val liveData:MutableLiveData<List<Flower>> = MutableLiveData()
        if (temp?.size!=0 && temp!=null) { //если бд не пустая
            //liveData.postValue(temp)
            Log.d("InLOADING",temp.toString())
            return temp //возвращаю из бд
        }
        else {
            val dataNetwork = network.getFlowList(API_KEY_PIXABAY, "yellow+flowers", "photo").hits
            if (dataNetwork != null) {
                for (item in dataNetwork) { //преобразую для хранения в room
                    val newFlower = Flower(
                        0,
                        item.downloads.toString(),
                        item.tags.toString(),
                        item.largeImageURL.toString()
                    )
                    data?.add(newFlower)
                }
                flowerDao?.saveAll(data)
                liveData.postValue(data)
            }
            return data //возвращаю только что полученные из бд данные
        }
        }
    }
