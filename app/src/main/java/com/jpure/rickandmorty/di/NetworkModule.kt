package com.jpure.rickandmorty.di

import com.jpure.rickandmorty.data.remote.RickAndMortyService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRickAndMortyService(): RickAndMortyService {
        return RickAndMortyService.create()
    }
}