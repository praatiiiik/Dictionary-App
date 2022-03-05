package com.example.dictionaryapp.di

import com.example.dictionaryapp.data.DefaultMainRepo
import com.example.dictionaryapp.data.MainRepo
import com.example.dictionaryapp.remote.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * it provides instance of main repo
     */
    @Singleton
    @Provides
    fun providesMainRepo(networkService: NetworkService) : MainRepo = DefaultMainRepo(networkService)
}