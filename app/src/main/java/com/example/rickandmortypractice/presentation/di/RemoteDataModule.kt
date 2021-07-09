package com.example.rickandmortypractice.presentation.di

import com.example.rickandmortypractice.data.api.RickAndMortyAPIService
import com.example.rickandmortypractice.data.repository.dataSource.CharacterRemoteDataSource
import com.example.rickandmortypractice.data.repository.dataSourceImplementation.CharacterRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providerCharacterRemoteDataSource(rickAndMortyAPIService: RickAndMortyAPIService) : CharacterRemoteDataSourceImpl {
        return CharacterRemoteDataSourceImpl(rickAndMortyAPIService)
    }


}