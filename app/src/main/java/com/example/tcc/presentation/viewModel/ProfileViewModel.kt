package com.example.tcc.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.dao.StudentDAO
import com.example.tcc.data.db.dao.TeacherDAO
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val studentDao: StudentDAO,
    private val teacherDao: TeacherDAO
) : ViewModel() {


    //mutavel pode ser lido e escrito
    private val _userName = MutableLiveData<String>()
    //so pode ser lido
    val userName: LiveData<String> = _userName

    fun loadUserName() {
        viewModelScope.launch {
            val userId = AppSingleton.userId
            val name = if (AppSingleton.isTeacher) {
                //carrega nome prof
                teacherDao.getTeacherBy(userId).name
            } else {
                // carrega nome aluno
                studentDao.getStudentBy(userId).name
            }
            _userName.postValue(name)
        }
    }
}