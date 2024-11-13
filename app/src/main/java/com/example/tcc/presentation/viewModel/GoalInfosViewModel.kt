package com.example.tcc.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.AppApplication
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import kotlinx.coroutines.launch

class GoalInfosViewModel : ViewModel() {

    private val appDataBase = AppDataBase.getInstance(AppApplication.getInstance())
    var teacherCellphone = ""
        private set
    var teacherEmail = ""
        private set
    val teamInstagramAt = MutableLiveData<String>()

    fun fetchData() {
        viewModelScope.launch {
            val classId = appDataBase.studentDAO().getStudentBy(AppSingleton.userId).classId
            val courseId = appDataBase.classDAO().getClassBy(classId).courseId
            val teamId = appDataBase.courseDAO().getCourseBy(courseId).teamId
            val team = appDataBase.teamDAO().getTeamBy(teamId)
            val teacher = appDataBase.teacherDAO().getTeacherByTeamId(teamId)

            teacherCellphone = teacher.cellphone
            teacherEmail = teacher.email
            teamInstagramAt.postValue(team.instagramAt)
        }
    }
}