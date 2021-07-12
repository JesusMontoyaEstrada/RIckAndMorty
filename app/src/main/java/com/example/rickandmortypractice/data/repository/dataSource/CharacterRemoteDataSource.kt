package com.example.rickandmortypractice.data.repository.dataSource

import androidx.paging.PagingData
import com.example.rickandmortypractice.data.model.APIResponse
import com.example.rickandmortypractice.data.model.Character
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CharacterRemoteDataSource {

    suspend fun getSearchedCharacters(query : String? = null, page : Int) : Response<APIResponse>


}