package com.example.rickandmortypractice.data.repository.dataSourceImplementation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortypractice.data.api.RickAndMortyAPIService
import com.example.rickandmortypractice.data.model.APIResponse
import com.example.rickandmortypractice.data.model.Character
import com.example.rickandmortypractice.data.model.Info
import com.example.rickandmortypractice.data.repository.dataSource.CharacterRemoteDataSource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


private const val CHARACTER_STARTING_PAGE_INDEX = 1
private const val NETWORK_PAGE_SIZE = 50
class CharacterRemoteDataSourceImpl (
        private val characterAPIService: RickAndMortyAPIService,
    ): CharacterRemoteDataSource, PagingSource<Int, Character>() {

    var query : String? = null

//    override suspend fun getCharacters(page: Int): Response<APIResponse> {
//        return characterAPIService.getCharacters(page)
//    }

    override suspend fun getSearchedCharacters(query: String?, page: Int): Response<APIResponse> {
        return characterAPIService.getSearchedCharacters(page, query)
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: CHARACTER_STARTING_PAGE_INDEX
        return try {

            val response = getSearchedCharacters(query, page)
            val info : Info? = response.body()?.info
            val repos = response.body()?.results
                ?: arrayListOf()

            var nextKey = if (page == info?.pages) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
//                page + (params.loadSize / NETWORK_PAGE_SIZE)
                page.plus(1)
            }
            if(repos.isEmpty()){
                nextKey = null
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (page == CHARACTER_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}