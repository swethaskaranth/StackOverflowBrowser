package com.kaizencoder.stackoverflowbrowser.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaizencoder.stackoverflowbrowser.ui.QuestionDetailViewModel

@Composable
fun QuestionDetailScreen(
    questionId: Int,
    modifier: Modifier = Modifier,
    viewModel: QuestionDetailViewModel = hiltViewModel()
) {
    val questionWithBody by viewModel.question.collectAsState()

    LaunchedEffect(true) {
        viewModel.getQuestionDetails(questionId)
    }

    questionWithBody?.let { question ->
        Column(
            modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        ) {
            Text(text = question.title)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(Icons.Default.AccountCircle, contentDescription = "profile")
                    Text(text = question.owner?.display_name ?: "")
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "Apr 9, 2020")
                }
            }

            Text(text = question.body)


        }
    }

}