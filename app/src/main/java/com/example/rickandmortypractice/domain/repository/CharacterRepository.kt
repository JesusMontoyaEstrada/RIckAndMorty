package com.example.rickandmortypractice.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortypractice.data.model.APIResponse
import com.example.rickandmortypractice.data.model.Character
import com.example.rickandmortypractice.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {

//    suspend fun getCharacters(page : Int) : Resource<APIResponse>
//    suspend fun getSearchedCharacter(query : String, page : Int) : Resource<APIResponse>

//    fun getCharacters() : Flow<PagingData<Character>>
    fun getSearchedCharacter(query : String? = null) : Flow<PagingData<Character>>

    fun getResultStream(query : String? = null): Flow<PagingData<Character>>

}