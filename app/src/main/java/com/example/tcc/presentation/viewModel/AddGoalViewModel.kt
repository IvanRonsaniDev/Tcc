package com.example.tcc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.AppApplication
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.data.repository.GoalRepository
import kotlinx.coroutines.launch

class AddGoalViewModel(private val goalRepository: GoalRepository = GoalRepository()) :
    ViewModel() {

    private val appDataBase = AppDataBase.getInstance(AppApplication.getInstance())
    var userTeamId: Long = -1L
        private set

    // Função para adicionar uma nova meta
    fun addGoal(goal: GoalEntity) {
        viewModelScope.launch {
            goalRepository.addGoal(goal)
        }
    }

    fun fetchData() {
        viewModelScope.launch {
            userTeamId = if (AppSingleton.isTeacher) {
                appDataBase.teacherDAO().getTeacherBy(AppSingleton.userId).teamId
            } else {
                val classId = appDataBase.studentDAO().getStudentBy(AppSingleton.userId).classId
                val courseId = appDataBase.classDAO().getClassBy(classId).courseId
                val teamId = appDataBase.courseDAO().getCourseBy(courseId).teamId
                appDataBase.teamDAO().getTeamBy(teamId).id
            }
        }
    }
}
