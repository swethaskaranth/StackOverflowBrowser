package com.kaizencoder.stackoverflowbrowser.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.model.QuestionWithBody
import com.kaizencoder.stackoverflowbrowser.networking.StackOverflowApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val stackOverflowApi: StackOverflowApi) {

    fun getQuestions(): Flow<PagingData<Question>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = { QuestionsPagingSource(stackOverflowApi) }
    ).flow

    fun getQuestionDetail(id: Int) : Flow<QuestionWithBody> = flow {
        val response = stackOverflowApi.getQuestionDetail(id)
        emit(response.questions[0])
    }
}