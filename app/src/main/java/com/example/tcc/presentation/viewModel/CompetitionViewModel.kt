package com.example.tcc.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.AppApplication
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.data.db.entities.TeamEntity
import kotlinx.coroutines.launch

class CompetitionViewModel : ViewModel() {

    private val appDataBase = AppDataBase.getInstance(AppApplication.getInstance())
    val goals = MutableLiveData<List<GoalEntity>>()
    val teams = MutableLiveData<List<TeamEntity>>()

    fun fetchData() {
        getGoals()
        getTeams()
    }

    fun updateTeam(team: TeamEntity) {
        viewModelScope.launch {
            appDataBase.teamDAO().update(team)
            teams.postValue(appDataBase.teamDAO().getAll())
        }
    }

    private fun getGoals() {
        viewModelScope.launch {
            val userTeamId = if (AppSingleton.isTeacher) {
                appDataBase.teacherDAO().getTeacherBy(AppSingleton.userId).teamId
            } else {
                val classId = appDataBase.studentDAO().getStudentBy(AppSingleton.userId).classId
                val courseId = appDataBase.classDAO().getClassBy(classId).courseId
                appDataBase.courseDAO().getCourseBy(courseId).teamId
            }
            goals.postValue(appDataBase.goalDAO().getGoalsForTeam(userTeamId))
        }
    }

    private fun getTeams() {
        viewModelScope.launch {
            teams.postValue(appDataBase.teamDAO().getAll())
        }
    }

}