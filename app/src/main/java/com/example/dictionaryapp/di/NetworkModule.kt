package com.example.dictionaryapp.di

import com.example.dictionaryapp.remote.NetworkService
import com.example.dictionaryapp.remote.Networking
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /**
     * it provides singleton instance of network service
     */
    @Singleton
    @Provides
    fun providesNetworkService() : NetworkService = Networking.createDictionaryInstance()

}