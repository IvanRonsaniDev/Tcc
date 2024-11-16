package com.example.tcc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tcc.data.db.dao.StudentDAO
import com.example.tcc.data.db.dao.TeacherDAO

class ProfileViewModelFactory(
    private val studentDao: StudentDAO,
    private val teacherDao: TeacherDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(studentDao, teacherDao) as T
        }
        throw IllegalArgumentException("erro")
    }
}
