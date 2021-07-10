package com.example.rickandmortypractice.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortypractice.data.model.APIResponse
import com.example.rickandmortypractice.data.model.Character
import com.example.rickandmortypractice.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

    fun getSearchedCharacter(query : String? = null) : Flow<PagingData<Character>>

    fun getResultStream(query : String? = null): Flow<PagingData<Character>>

}