package com.example.rickandmortypractice.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortypractice.data.api.RickAndMortyAPIService
import com.example.rickandmortypractice.data.model.Character
import com.example.rickandmortypractice.data.model.Info
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

private const val CHARACTER_STARTING_PAGE_INDEX = 1
private const val NETWORK_PAGE_SIZE = 50
class CharacterPagingSource(
    private val service : RickAndMortyAPIService,
    private val query: String? = null
): PagingSource<Int, Character>(){


    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: CHARACTER_STARTING_PAGE_INDEX
        return try {

            val response = service.getSearchedCharacters(page,query)
            val info : Info = response.body()?.info!!
            val repos = response.body()?.results!!

            val nextKey = if (page == info.count) {
                null
            } else {
//                page.plus(1)
                page + (params.loadSize / NETWORK_PAGE_SIZE)
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
        }  catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }


}