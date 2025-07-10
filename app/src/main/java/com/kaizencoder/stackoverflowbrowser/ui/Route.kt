package com.kaizencoder.stackoverflowbrowser.ui

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object ListScreen: Route()
    @Serializable
    data class DetailScreen(val questionId: Int): Route()
}