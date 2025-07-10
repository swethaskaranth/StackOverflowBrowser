package com.kaizencoder.stackoverflowbrowser.networking

import com.kaizencoder.stackoverflowbrowser.model.QuestionWithBody
import com.squareup.moshi.Json

data class QuestionDetailResponseSchema(@Json(name = "items") val questions: List<QuestionWithBody>)
