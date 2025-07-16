package com.kaizencoder.stackoverflowbrowser.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.kaizencoder.stackoverflowbrowser.model.Owner
import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.ui.QuestionListViewModel


@Composable
fun QuestionListScreen(
    onNavigateToDetailScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestionListViewModel = hiltViewModel()
) {
    val questions = viewModel.questions.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(questions.itemCount){ index ->

            questions[index]?.let {
                QuestionItem(it) { questionId ->
                    onNavigateToDetailScreen(questionId)
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
        Text(text = question.owner.display_name,
            modifier = Modifier.padding(top = 8.dp, start = 12.dp, bottom = 12.dp))

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