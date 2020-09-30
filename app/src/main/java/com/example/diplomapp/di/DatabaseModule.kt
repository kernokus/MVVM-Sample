package com.example.diplomapp.di

import android.content.Context
import com.example.diplomapp.repo.RoomRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRoomModule(@ApplicationContext context: Context): RoomRepo {
        return RoomRepo(context)
    }
}
