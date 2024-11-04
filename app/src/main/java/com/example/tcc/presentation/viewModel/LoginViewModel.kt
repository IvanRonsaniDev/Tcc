package com.example.tcc.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.data.db.AppDataBase
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    val isUserValid = MutableLiveData<Boolean>()

    private val studentDAO = AppDataBase.getInstance().studentDAO()
    private val teacherDAO = AppDataBase.getInstance().teacherDAO()

    fun isUserValid(login: String, password: String, isTeacher: Boolean) {
        viewModelScope.launch {
            isUserValid.postValue(
                if (isTeacher) {
                    teacherDAO.isTeacherExists(login, password)
                } else {
                    studentDAO.isStudentExists(login, password)
                }
            )
        }
    }

}