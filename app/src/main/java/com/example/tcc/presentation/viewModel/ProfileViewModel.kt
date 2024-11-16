package com.example.tcc.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.dao.ClassDAO
import com.example.tcc.data.db.dao.CourseDAO
import com.example.tcc.data.db.dao.StudentDAO
import com.example.tcc.data.db.dao.TeacherDAO
import com.example.tcc.data.db.dao.TeamDAO
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val studentDao: StudentDAO,
    private val teacherDao: TeacherDAO,
    private val classDao: ClassDAO,
    private val courseDAO: CourseDAO,
    private val teamDAO: TeamDAO
) : ViewModel() {

    //mutavel pode ser lido e escrito
    private val _userName = MutableLiveData<String>()

    //so pode ser lido
    val userName: LiveData<String> = _userName

    private val _nameInitials = MutableLiveData<String>()
    val nameInitials: LiveData<String> = _nameInitials

    private val _classTeam = MutableLiveData<String>()
    val classTeam: LiveData<String> = _classTeam

    private val _studentRegistrationNumber = MutableLiveData<String?>()
    val studentRegistrationNumber: LiveData<String?> = _studentRegistrationNumber

    fun loadUserName() {
        viewModelScope.launch {
            val userId = AppSingleton.userId
            val teacherWithTeam =
                if (AppSingleton.isTeacher) teacherDao.getTeacherWithTeamById(userId) else null
            val student = if (!AppSingleton.isTeacher) studentDao.getStudentBy(userId) else null

            // student team name
            val studentTeamName = student?.classId?.let {
                val courseId = classDao.getClassBy(it).courseId
                val teamId = courseDAO.getCourseBy(courseId).teamId
                teamDAO.getTeamBy(teamId).name
            }.orEmpty()

            // student class name
            val studentClassName = student?.classId?.let {
                classDao.getClassBy(it).name
            }.orEmpty()


            val name = (teacherWithTeam?.teacher?.name ?: student?.name).orEmpty()
            _userName.postValue(name)
            _nameInitials.postValue(name.getNameInitials())
            _classTeam.postValue(
                teacherWithTeam?.team?.name ?: "$studentClassName / $studentTeamName"
            )
            _studentRegistrationNumber.postValue(student?.registrationNumber)
        }
    }

    // Lucas Rodrigues Moro
    private fun String.getNameInitials(): String {
        val firstNameChar = getFirstNameChar().uppercase()
        val surnameChar = getSurnameChar()?.uppercase().orEmpty()

        return firstNameChar + surnameChar
    }

    // Lucas
    // ["L", "u", "c", "a", "s"]
    private fun String.getFirstNameChar() = split(" ").first().first()

    // Rodrigues Moro
    private fun String.getSurnameChar() = split(" ").getOrNull(1)?.first()
}