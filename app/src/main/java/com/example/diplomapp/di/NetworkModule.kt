package com.example.diplomapp.di

import android.util.Log
import com.example.diplomapp.api.FlowService
import com.example.diplomapp.api.FlowService.Companion.BASE_URL_PIXABAY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

class NetworkModule {

    @Module
    @InstallIn(ApplicationComponent::class)
    class NetwModule {
        @Provides
        @Singleton
        fun provideNetwork(): FlowService.Companion {
            return FlowService
        }
    }
}