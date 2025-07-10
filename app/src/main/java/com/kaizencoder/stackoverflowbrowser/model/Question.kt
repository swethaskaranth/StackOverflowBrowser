package com.kaizencoder.stackoverflowbrowser.model

data class Question(
    val owner: Owner,
    val question_id: Int,
    val title: String,
    val accepted_answer_id: Int = 0,
    val answer_count: Int = 0,
    val is_answered: Boolean = false,
    val link: String = "",
    val view_count: Int = 0
)

data class Owner(
    val display_name: String,
    val profile_image: String?
)