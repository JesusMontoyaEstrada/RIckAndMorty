package com.example.rickandmortypractice.presentation.di

import com.example.rickandmortypractice.domain.repository.CharacterRepository
import com.example.rickandmortypractice.domain.usecase.GetSearchedCharacterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providerGetSearchedCharacterUseCase(characterRepository: CharacterRepository) : GetSearchedCharacterUseCase {
        return GetSearchedCharacterUseCase(characterRepository)
    }

}