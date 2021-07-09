package com.example.rickandmortypractice.presentation.di

import android.app.Application
import com.example.rickandmortypractice.data.repository.CharacterPagingSource
import com.example.rickandmortypractice.data.repository.CharacterStreamRepository
import com.example.rickandmortypractice.domain.usecase.GetCharactersUseCase
import com.example.rickandmortypractice.domain.usecase.GetSearchedCharacterUseCase
import com.example.rickandmortypractice.presentation.viewmodel.CharacterViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ViewModelFactoryModule {

    @Singleton
    @Provides
    fun providerCharacterViewModelFactory(
        application: Application,
//        getCharactersUseCase: GetCharactersUseCase,
        getSearchedCharacterUseCase: GetSearchedCharacterUseCase,
        characterStreamRepository: CharacterStreamRepository
    ) : CharacterViewModelFactory {
        return CharacterViewModelFactory(application
//            , getCharactersUseCase
            , getSearchedCharacterUseCase,
        characterStreamRepository)
    }

}