package com.example.diplomapp.viewModels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.diplomapp.repo.RoomRepo


class MainViewModel @ViewModelInject constructor(val roomRepo: RoomRepo) :ViewModel() {

    init {
        //loadContent() need to use viewModelScope
    }

    private suspend fun loadContent() {
        roomRepo.loadInDD()
    }

}