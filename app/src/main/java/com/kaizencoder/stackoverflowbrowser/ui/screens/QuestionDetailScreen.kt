package com.kaizencoder.stackoverflowbrowser.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaizencoder.stackoverflowbrowser.model.Owner
import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.model.QuestionWithBody

@Composable
fun QuestionDetailScreen(question: QuestionWithBody, modifier: Modifier = Modifier) {
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
                Text(text = question.owner.display_name)
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(text = "Apr 9, 2020")
            }
        }


    }
}

@Preview(showSystemUi = true)
@Composable
private fun QuestionDetailPreview() {
    val owner = Owner("Test Coder", "")
    QuestionDetailScreen(
        QuestionWithBody( 1, 0,"",0,"", 0,true,0,0, "", 0,owner,0, 1, 2, emptyList(),"This is a test question",0 ),
        modifier = Modifier.padding(vertical = 48.dp)
    )
}