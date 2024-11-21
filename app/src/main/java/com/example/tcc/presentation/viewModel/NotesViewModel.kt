package com.example.tcc.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.AppApplication
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.data.db.entities.StudentEntity
import com.example.tcc.data.db.entities.TeamEntity
import com.example.tcc.presentation.viewModel.NotesViewModel
import kotlinx.coroutines.launch


class NotesViewModel: ViewModel() {

    private val appDataBase = AppDataBase.getInstance(AppApplication.getInstance())
    val students = MutableLiveData<List<StudentEntity>>()

    fun fetchData() {
        getStudents()

    }

    fun updateTeam(team: TeamEntity) {
        viewModelScope.launch {
            appDataBase.teamDAO().update(team)
            teams.postValue(appDataBase.teamDAO().getAll())
        }

    //EditNotesDialog
}