package com.example.tcc.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tcc.data.repository.GoalRepository

class GoalViewModelFactory(private val goalRepository: GoalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GoalViewModel(goalRepository) as T
    }
}
