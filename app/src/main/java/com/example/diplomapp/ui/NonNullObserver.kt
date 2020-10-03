package com.example.diplomapp.ui

import androidx.lifecycle.Observer

interface NonNullObserver<T>: Observer<T> {

    override fun onChanged(t: T?) {
        if (t != null) onChangedNonNull(t)
    }

    fun onChangedNonNull(t: T)
}