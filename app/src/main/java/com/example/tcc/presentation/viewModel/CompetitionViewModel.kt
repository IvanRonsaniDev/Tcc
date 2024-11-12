package com.example.tcc.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.AppApplication
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.GoalEntity
import kotlinx.coroutines.launch

class CompetitionViewModel : ViewModel() {

    private val appDataBase = AppDataBase.getInstance(AppApplication.getInstance())
    val goals = MutableLiveData<List<GoalEntity>>()

    fun getGoals() {
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

}