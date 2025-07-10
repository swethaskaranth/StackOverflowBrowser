package com.kaizencoder.stackoverflowbrowser.networking

import com.kaizencoder.stackoverflowbrowser.Constants
import retrofit2.http.GET

interface StackOverflowApi {

    @GET("questions?key="+ Constants.API_KEY +"&order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestions(): QuestionsListResponseSchema
}