package com.kaizencoder.stackoverflowbrowser.networking

import com.kaizencoder.stackoverflowbrowser.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackOverflowApi {

    @GET("questions?key="+ Constants.API_KEY +"&order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestions(@Query("page") page: Int, @Query("pagesize") pageSize: Int): QuestionsListResponseSchema

    @GET("questions/{questionId}?key=" + Constants.API_KEY + "&site=stackoverflow&filter=withbody")
    suspend fun getQuestionDetail(@Path("questionId")id: Int): QuestionDetailResponseSchema
}