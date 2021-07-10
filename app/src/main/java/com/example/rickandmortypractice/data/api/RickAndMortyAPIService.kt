package com.example.rickandmortypractice.data.api

import com.example.rickandmortypractice.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyAPIService {


    @GET("character")
    suspend fun getSearchedCharacters(
        @Query("page") page : Int,
        @Query("name") name : String? = null
    ) : Response<APIResponse>

}