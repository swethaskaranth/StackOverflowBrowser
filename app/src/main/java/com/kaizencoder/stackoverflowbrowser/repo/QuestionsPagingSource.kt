package com.kaizencoder.stackoverflowbrowser.repo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.networking.StackOverflowApi

class QuestionsPagingSource(private val stackOverflowApi: StackOverflowApi): PagingSource<Int, Question>() {

    override fun getRefreshKey(state: PagingState<Int, Question>): Int? {
       return state.anchorPosition?.let{ anchorPosition ->
           state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
               ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
       }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Question> {
        val page = params.key ?: 1
        return try{
            val response = stackOverflowApi.getQuestions(page, params.loadSize)
            LoadResult.Page(
                data = response.items,
                prevKey = if(page == 1) null else page -1,
                nextKey = if(response.items.isEmpty()) null else page + 1
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}