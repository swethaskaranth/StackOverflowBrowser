package com.kaizencoder.stackoverflowbrowser.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kaizencoder.stackoverflowbrowser.model.Owner
import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.ui.QuestionListViewModel

@Composable
fun QuestionListScreen(
    modifier: Modifier = Modifier,
    viewModel: QuestionListViewModel = hiltViewModel()
) {
    val questions = viewModel.questions.collectAsState()
    LazyColumn(
        modifier
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(questions.value) { question ->
            QuestionItem(question)
        }
    }

}

@Composable
fun QuestionItem(question: Question, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = question.title)
        Text(text = question.owner.display_name)
    }
}

@Preview
@Composable
private fun QuestionItemPreview() {
    val owner = Owner("Test Coder", "")
    QuestionItem(Question(owner, 1, "This is a test question"))

}