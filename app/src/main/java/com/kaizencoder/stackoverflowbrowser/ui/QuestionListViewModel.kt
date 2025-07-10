package com.kaizencoder.stackoverflowbrowser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.repo.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionListViewModel @Inject constructor(private val questionRepository: QuestionRepository): ViewModel() {

    private val _questions = MutableStateFlow<List<Question>>(emptyList())
    val questions: StateFlow<List<Question>> = _questions.asStateFlow()

    init {
        fetchQuestions()
    }

    fun fetchQuestions(){
        viewModelScope.launch {
            questionRepository.getQuestions()
                .collect { questions ->
                    _questions.value = questions
                }
        }
    }
}