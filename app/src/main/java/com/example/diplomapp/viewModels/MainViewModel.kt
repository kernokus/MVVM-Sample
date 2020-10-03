package com.example.diplomapp.viewModels


import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplomapp.repo.RoomRepo
import com.example.diplomapp.room.Flower
import kotlinx.coroutines.*
import java.lang.Exception


class MainViewModel @ViewModelInject constructor(private val roomRepo: RoomRepo) :ViewModel() {
    //private val flowerList : MutableLiveData<List<Flower>> = Muta

    var flowerList : MutableLiveData<List<Flower>> = MutableLiveData(emptyList()) //empty list

        //private var temp:List<Flower>? = emptyList()
    init {
        //lowerList.value=temp
        viewModelScope.launch(Dispatchers.IO) {
            val temp=roomRepo.loadInDD()
            if (temp!=null) {
                flowerList.postValue(temp)
            }
        }

    }
    fun getListFlowers() = flowerList

//    private suspend fun loadContent()= withContext(Dispatchers.IO) {
//        flowerList=roomRepo.loadInDD()
//    }



}