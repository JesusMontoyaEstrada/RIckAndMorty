package com.example.rickandmortypractice.presentation.di

import com.example.rickandmortypractice.presentation.adapter.CharacterAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providerCharacterAdapter() : CharacterAdapter {
        return CharacterAdapter()
    }
}