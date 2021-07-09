package com.example.rickandmortypractice.presentation.di

import com.example.rickandmortypractice.data.api.RickAndMortyAPIService
import com.example.rickandmortypractice.data.repository.CharacterRepositoryImpl
import com.example.rickandmortypractice.data.repository.CharacterStreamRepository
import com.example.rickandmortypractice.data.repository.dataSourceImplementation.CharacterRemoteDataSourceImpl
import com.example.rickandmortypractice.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providerCharacterRepository(characterRemoteDataSource: CharacterRemoteDataSourceImpl) : CharacterRepository {
        return CharacterRepositoryImpl(characterRemoteDataSource)
    }


    @Singleton
    @Provides
    fun providerCharacterStreamRepository(rickAndMortyAPIService: RickAndMortyAPIService) : CharacterStreamRepository {
        return CharacterStreamRepository(rickAndMortyAPIService)
    }

}