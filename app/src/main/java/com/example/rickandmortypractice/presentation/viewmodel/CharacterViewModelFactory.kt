package com.example.rickandmortypractice.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortypractice.data.repository.CharacterPagingSource
import com.example.rickandmortypractice.data.repository.CharacterStreamRepository
import com.example.rickandmortypractice.domain.usecase.GetCharactersUseCase
import com.example.rickandmortypractice.domain.usecase.GetSearchedCharacterUseCase

class CharacterViewModelFactory(
    private val app : Application,
//    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSearchedCharacterUseCase: GetSearchedCharacterUseCase,
    private val characterStreamRepository: CharacterStreamRepository
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterViewModel(app
//            , getCharactersUseCase
            , getSearchedCharacterUseCase,characterStreamRepository) as T
    }

}