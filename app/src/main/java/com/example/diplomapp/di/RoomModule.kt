package com.example.diplomapp.di

import android.content.Context
import androidx.room.Room

import com.example.diplomapp.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(context, AppDatabase::class.java, "db").build()

    @Provides
    @Singleton
    fun provideFlowDao(appDatabase: AppDatabase) = appDatabase.flowerDao()
}