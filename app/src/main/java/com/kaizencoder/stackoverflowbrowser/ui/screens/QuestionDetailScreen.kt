package com.kaizencoder.stackoverflowbrowser.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaizencoder.stackoverflowbrowser.ui.QuestionDetailViewModel
import com.kaizencoder.stackoverflowbrowser.utils.DateFormatter

@Composable
fun QuestionDetailScreen(
    questionId: Int,
    viewModel: QuestionDetailViewModel = hiltViewModel()
) {
    val questionWithBody by viewModel.question.collectAsState()

    LaunchedEffect(true) {
        viewModel.getQuestionDetails(questionId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        questionWithBody?.let { question ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = question.title,
                    fontSize = 24.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = "profile",
                            tint = Color.White
                        )
                        Text(text = question.owner?.display_name ?: "")
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        question.creation_date?.let {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Clock"
                            )
                            Text(
                                text = DateFormatter.convertTimestampToFormattedDate(
                                    it
                                )
                            )
                        }
                    }
                }
                val questionBody = buildAnnotatedString {
                    append(HtmlCompat.fromHtml(question.body, FROM_HTML_MODE_COMPACT))
                }

                Text(text = questionBody)


            }
        }
    }


}