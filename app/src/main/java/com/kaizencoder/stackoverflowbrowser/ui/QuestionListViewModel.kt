package com.kaizencoder.stackoverflowbrowser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kaizencoder.stackoverflowbrowser.model.Question
import com.kaizencoder.stackoverflowbrowser.repo.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionListViewModel @Inject constructor(private val questionRepository: QuestionRepository): ViewModel() {

    val questions: Flow<PagingData<Question>> =questionRepository.getQuestions()
        .cachedIn(viewModelScope)

}