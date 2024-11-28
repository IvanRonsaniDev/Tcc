package com.example.tcc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.data.repository.ActivityRepository
import com.example.tcc.data.repository.GoalRepository
import kotlinx.coroutines.launch

class ActivityViewModel(private val activityRepository: ActivityRepository = ActivityRepository()) :
    ViewModel() {

    fun deleteActivity(activity: ActivityEntity) {
        viewModelScope.launch {
            activityRepository.deleteActivity(activity) // Chama o repositório para excluir
        }
    }
}