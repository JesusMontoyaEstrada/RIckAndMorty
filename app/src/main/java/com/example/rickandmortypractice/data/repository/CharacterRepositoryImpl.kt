package com.example.rickandmortypractice.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortypractice.data.api.RickAndMortyAPIService
import com.example.rickandmortypractice.data.model.APIResponse
import com.example.rickandmortypractice.data.model.Character
import com.example.rickandmortypractice.data.repository.dataSourceImplementation.CharacterRemoteDataSourceImpl
import com.example.rickandmortypractice.data.util.Resource
import com.example.rickandmortypractice.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


class CharacterRepositoryImpl (
    private val characterRemoteDataSource: CharacterRemoteDataSourceImpl

): CharacterRepository{

    override fun getSearchedCharacter(query: String?): Flow<PagingData<Character>> {
        return getResultStream(query)
    }

    private fun responseToResource(response : Response<APIResponse>) : Resource<APIResponse> {
        if(response.isSuccessful){
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }

        return Resource.Error(response.message())
    }

    override fun getResultStream(query : String?): Flow<PagingData<Character>>{
        characterRemoteDataSource.query = query
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {  characterRemoteDataSource }

        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }


}