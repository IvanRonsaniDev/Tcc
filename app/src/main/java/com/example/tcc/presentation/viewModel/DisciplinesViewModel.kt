package com.example.tcc.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.AppApplication
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.DisciplineEntity
import com.example.tcc.data.db.entities.TeacherWithDisciplines
import kotlinx.coroutines.launch

class DisciplinesViewModel : ViewModel() {

    private val _disciplines = MutableLiveData<List<DisciplineEntity>>()
    val disciplines: LiveData<List<DisciplineEntity>> = _disciplines

    fun getDisciplines() {
        viewModelScope.launch {
            _disciplines.postValue(
                if (AppSingleton.isTeacher) {
                    AppDataBase.getInstance(AppApplication.getInstance()).teacherDAO()
                        .getTeacherWithDisciplines(
                            AppSingleton.userId
                        ).disciplines
                } else {
                    AppDataBase.getInstance(AppApplication.getInstance()).disciplineDAO()
                        .getAll()
                }
            )
        }
    }

}