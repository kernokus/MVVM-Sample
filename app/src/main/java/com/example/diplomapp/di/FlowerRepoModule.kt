package com.example.diplomapp.di

import android.content.Context
import com.example.diplomapp.repo.FlowerRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class FlowerRepoModule {
    @Provides
    @Singleton
    fun provideFlowerModule(@ApplicationContext context: Context): FlowerRepo {
        return FlowerRepo(context)
    }
}
