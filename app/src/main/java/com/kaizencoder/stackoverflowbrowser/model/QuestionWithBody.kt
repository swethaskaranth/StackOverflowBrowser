package com.kaizencoder.stackoverflowbrowser.model

data class QuestionWithBody(
    val answer_count: Int?,
    val body: String,
    val closed_date: Int?,
    val closed_reason: String?,
    val creation_date: Int?,
    val is_answered: Boolean,
    val last_activity_date: Int?,
    val last_edit_date: Int?,
    val link: String?,
    val locked_date: Int?,
    val owner: Owner?,
    val protected_date: Int?,
    val question_id: Int,
    val score: Int?,
    val tags: List<String>?,
    val title: String,
    val view_count: Int?
)

/*
data class Owner(
    val accept_rate: Int,
    val account_id: Int,
    val display_name: String,
    val link: String,
    val profile_image: String,
    val reputation: Int,
    val user_id: Int,
    val user_type: String
)*/
