package com.example.rickandmortypractice.presentation.viewmodel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortypractice.data.model.Character
import com.example.rickandmortypractice.domain.usecase.GetSearchedCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class CharacterViewModels @Inject constructor (
    private val app : Application,
    private val getSearchedCharacterUseCase: GetSearchedCharacterUseCase
    ): AndroidViewModel(app){

    private var currentSearchResult: Flow<PagingData<Character>>? = null

    fun getCharacters(queryString : String? = null) : Flow<PagingData<Character>> {
        val newResult: Flow<PagingData<Character>> = getSearchedCharacterUseCase.execute(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}