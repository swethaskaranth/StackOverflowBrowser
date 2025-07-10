package com.kaizencoder.stackoverflowbrowser.repo

import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.networking.QuestionsListResponseSchema
import com.kaizencoder.stackoverflowbrowser.networking.StackOverflowApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuestionRepository @Inject constructor(private val stackOverflowApi: StackOverflowApi) {

    fun getQuestions(): Flow<List<Question>> {
        return flow<QuestionsListResponseSchema> { emit(stackOverflowApi.getQuestions()) }
            .map { questionListSchema ->
                questionListSchema.items
            }
    }
}