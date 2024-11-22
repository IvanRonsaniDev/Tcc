package com.example.tcc.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tcc.AppApplication
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.data.db.entities.ClassEntity
import com.example.tcc.data.db.entities.NoteEntity
import com.example.tcc.data.db.entities.StudentWithNote
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {

    private var classId: Long = -1
    private var disciplineId: Long = -1
    private var activityId: Long = -1
    private val appDataBase = AppDataBase.getInstance(AppApplication.getInstance())

    private val _students = MutableLiveData<List<StudentWithNote>>()
    val students: LiveData<List<StudentWithNote>> = _students

    private val _classes = MutableLiveData<List<ClassEntity>>()
    val onGetClasses: LiveData<List<ClassEntity>> = _classes
    val classes: List<Pair<Long, String>>
        get() = _classes.value?.map { it.classId to it.name } ?: emptyList()
    val selectedClass: String
        get() = _classes.value?.find { it.classId == classId }?.name ?: ""

    private val _activities = MutableLiveData<List<ActivityEntity>>()
    val onGetActivities: LiveData<List<ActivityEntity>> = _activities
    val activities: List<Pair<Long, String>>
        get() = _activities.value?.map { it.id to it.title } ?: emptyList()
    val selectedActivity: String
        get() = _activities.value?.find { it.id == activityId }?.title ?: ""

    fun fetchData(disciplineId: Long) {
        this.disciplineId = disciplineId
        viewModelScope.launch {
            getClasses().join()
            getActivities().join()
            getStudentsWithNotes()
        }
    }

    fun updateNote(studentWithNote: StudentWithNote, note: Double) {
        viewModelScope.launch {
            studentWithNote.note?.let {
                appDataBase.notesDAO().update(
                    it.copy(note = note)
                )
            } ?: appDataBase.notesDAO().insert(
                NoteEntity(
                    studentId = studentWithNote.student.id,
                    activityId = activityId,
                    note = note
                )
            )
            getStudentsWithNotes()
        }
    }

    private fun getStudentsWithNotes() = viewModelScope.launch {
        appDataBase.studentDAO().getStudentsByClassId(classId).map {
            StudentWithNote(
                student = it,
                note = appDataBase.notesDAO().getNoteBy(it.id, activityId)
            )
        }.also {
            _students.postValue(it)
        }
    }

    private fun getClasses() = viewModelScope.launch {
        if (AppSingleton.isTeacher) {
            appDataBase.teacherDAO().getTeacherWithClasses(AppSingleton.userId).also {
                _classes.postValue(it.classes)
                it.classes.firstOrNull()?.classId?.let { id -> classId = id }
            }
        } else {
            val student = appDataBase.studentDAO().getStudentBy(AppSingleton.userId)
            appDataBase.classDAO().getClassBy(student.classId).also {
                classId = it.classId
                _classes.postValue(listOf(it))
            }
        }
    }

    private fun getActivities() = viewModelScope.launch {
        appDataBase.activityDAO().getEvaluativeActivitiesByClassAndDiscipline(
            classId = classId,
            disciplineId = disciplineId
        ).also {
            it.firstOrNull()?.let { activity -> activityId = activity.id }
            _activities.postValue(it)
        }
    }

    fun onSelectClass(classId: Long) {
        this.classId = classId
        getStudentsWithNotes()
    }

    fun onSelectActivity(activityId: Long) {
        this.activityId = activityId
        getStudentsWithNotes()
    }

}