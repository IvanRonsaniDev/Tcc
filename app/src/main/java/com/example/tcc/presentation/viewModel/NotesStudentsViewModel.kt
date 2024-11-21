package com.example.tcc.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.AppApplication
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.data.db.entities.TeamEntity
import kotlinx.coroutines.launch

class NotesStudentsViewModel: ViewModel() {

    private val appDataBase = AppDataBase.getInstance(AppApplication.getInstance())
    val disciplines = MutableLiveData<List<GoalEntity>>()
   //acj

    fun fetchData() {
        getDisciplines()

    }

    fun updateTeam(team: TeamEntity) {
        viewModelScope.launch {
            appDataBase.teamDAO().update(team)
            teams.postValue(appDataBase.teamDAO().getAll())
        }
    }
}