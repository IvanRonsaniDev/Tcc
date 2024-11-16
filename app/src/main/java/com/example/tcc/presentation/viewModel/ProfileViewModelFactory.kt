package com.example.tcc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tcc.data.db.dao.ClassDAO
import com.example.tcc.data.db.dao.CourseDAO
import com.example.tcc.data.db.dao.StudentDAO
import com.example.tcc.data.db.dao.TeacherDAO
import com.example.tcc.data.db.dao.TeamDAO

class ProfileViewModelFactory(
    private val studentDao: StudentDAO,
    private val teacherDao: TeacherDAO,
    private val classDao: ClassDAO,
    private val courseDAO: CourseDAO,
    private val teamDAO: TeamDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(studentDao, teacherDao, classDao, courseDAO, teamDAO) as T
        }
        throw IllegalArgumentException("erro")
    }
}
