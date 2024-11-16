package com.example.tcc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.AppApplication
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import kotlinx.coroutines.launch

class ChangePasswordViewModel : ViewModel() {

    private val appDataBase = AppDataBase.getInstance(AppApplication.getInstance())
    private val studentDAO = appDataBase.studentDAO()
    private val teacherDAO = appDataBase.teacherDAO()

    var currentPassword = ""
        private set

    fun fetchData() {
        viewModelScope.launch {
            currentPassword = if (AppSingleton.isTeacher) {
                teacherDAO.getTeacherBy(AppSingleton.userId).password
            } else {
                studentDAO.getStudentBy(AppSingleton.userId).password
            }
        }
    }

    fun savePassword(newPassword: String) {
        viewModelScope.launch {
            if (AppSingleton.isTeacher) {
                val teacher = teacherDAO.getTeacherBy(AppSingleton.userId)
                teacherDAO.update(teacher.copy(password = newPassword))
            } else {
                val student = studentDAO.getStudentBy(AppSingleton.userId)
                studentDAO.update(student.copy(password = newPassword))
            }
        }
    }

}