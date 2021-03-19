package com.jpure.rickandmorty.di

import com.jpure.rickandmorty.data.AppDatabase
import com.jpure.rickandmorty.data.RepositoryFactory
import com.jpure.rickandmorty.data.remote.RickAndMortyService
import com.jpure.rickandmorty.data.repository.ListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideListRepository(
        service: RickAndMortyService,
        db: AppDatabase
    ): ListRepository {
        return RepositoryFactory.makeListRepository(service, db)
    }

}