package com.example.diplomapp.viewModels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diplomapp.repo.FlowerRepo
import com.example.diplomapp.room.Flower
import kotlinx.coroutines.*


class MainViewModel @ViewModelInject constructor(private val roomRepo: FlowerRepo) :ViewModel() {
    var flowerList : MutableLiveData<List<Flower>> = MutableLiveData(emptyList()) //empty list

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val temp=roomRepo.loadInDD()
            if (temp!=null) {
                flowerList.postValue(temp)
            }
        }
    }
    fun getListFlowers() = flowerList
}