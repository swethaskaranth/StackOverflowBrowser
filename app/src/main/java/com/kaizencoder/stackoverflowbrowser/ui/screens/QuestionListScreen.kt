package com.kaizencoder.stackoverflowbrowser.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kaizencoder.stackoverflowbrowser.R
import com.kaizencoder.stackoverflowbrowser.model.Owner
import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.ui.QuestionListViewModel
import com.kaizencoder.stackoverflowbrowser.utils.DateFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionListScreen(
    onNavigateToDetailScreen: (Int) -> Unit,
    viewModel: QuestionListViewModel = hiltViewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val questions = viewModel.questions.collectAsLazyPagingItems()

        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(questions.itemCount) { index ->

                questions[index]?.let { question ->
                    QuestionItem(question) { questionId ->
                        onNavigateToDetailScreen(questionId)
                    }
                }
            }
        }
    }


}

@Composable
fun QuestionItem(question: Question, onItemClick: (Int) -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = MaterialTheme.shapes.extraSmall,
        onClick = {
            onItemClick(question.question_id)
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = question.title,
            modifier = Modifier.padding(top = 12.dp, start = 12.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 12.dp, bottom = 12.dp),
            horizontalAlignment = Alignment.End
        ) {
            question.creation_date?.let { date ->
                val annotatedString = buildAnnotatedString {
                    append("asked on ")
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.tertiary)) {
                        append(
                            DateFormatter.convertTimestampToFormattedDate(
                                date
                            )
                        )
                    }
                }
                Text(
                    text = annotatedString
                )
            }
            val ownerName = buildAnnotatedString {
                append(" by ")
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.secondary)) {
                    append(question.owner.display_name)
                }
            }
            Text(text = ownerName)
        }


    }

}

@Preview(showSystemUi = true)
@Composable
private fun QuestionItemPreview() {
    val owner = Owner("Test Coder", "")

    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(15) {
            QuestionItem(Question(owner, 1, "This is a test question")) {}
        }
    }

}