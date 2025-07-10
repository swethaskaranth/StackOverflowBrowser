package com.kaizencoder.stackoverflowbrowser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaizencoder.stackoverflowbrowser.model.QuestionWithBody
import com.kaizencoder.stackoverflowbrowser.repo.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionDetailViewModel @Inject constructor(private val repository: QuestionRepository): ViewModel() {

    private val _question = MutableStateFlow<QuestionWithBody?>(null)
    val question : StateFlow<QuestionWithBody?> = _question.asStateFlow()

    fun getQuestionDetails(questionId: Int){
        viewModelScope.launch {
            repository.getQuestionDetail(questionId)
                .collect {
                    _question.value = it
                }
        }
    }
}