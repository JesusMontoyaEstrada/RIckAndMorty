package com.example.rickandmortypractice.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortypractice.data.model.Character
import com.example.rickandmortypractice.data.repository.CharacterPagingSource
import com.example.rickandmortypractice.data.repository.CharacterStreamRepository
import com.example.rickandmortypractice.domain.usecase.GetCharactersUseCase
import com.example.rickandmortypractice.domain.usecase.GetSearchedCharacterUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CharacterViewModel (
    private val app : Application,
//    private val getCharactersUseCase: GetCharactersUseCase,
    private val getSearchedCharacterUseCase: GetSearchedCharacterUseCase,
    private val characterStreamRepository: CharacterStreamRepository
    ): AndroidViewModel(app){


    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Character>>? = null


    fun getCharacters(queryString : String? = null) : Flow<PagingData<Character>> {
        val newResult: Flow<PagingData<Character>> = getSearchedCharacterUseCase.execute(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    fun getStreamCharacters(queryString: String? = null) : Flow<PagingData<Character>> {
        val newResult: Flow<PagingData<Character>> = characterStreamRepository.getSearchResultStream(queryString)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

}