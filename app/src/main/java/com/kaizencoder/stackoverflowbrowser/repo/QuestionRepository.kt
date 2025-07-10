package com.kaizencoder.stackoverflowbrowser.repo

import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.model.QuestionWithBody
import com.kaizencoder.stackoverflowbrowser.networking.StackOverflowApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val stackOverflowApi: StackOverflowApi) {

    fun getQuestions(): Flow<List<Question>> = flow {
        val response = stackOverflowApi.getQuestions()
        emit(response.items)
    }

    fun getQuestionDetail(id: Int) : Flow<QuestionWithBody> = flow {
        val response = stackOverflowApi.getQuestionDetail(id)
        emit(response.questions[0])
    }
}