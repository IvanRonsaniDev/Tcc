package com.example.tcc

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.tcc.core.extensions.resetTime
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.data.db.entities.ClassEntity
import com.example.tcc.data.db.entities.CompetitionEntity
import com.example.tcc.data.db.entities.CourseEntity
import com.example.tcc.data.db.entities.DisciplineEntity
import com.example.tcc.data.db.entities.DisciplineTeacherCrossRef
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.data.db.entities.StudentEntity
import com.example.tcc.data.db.entities.TeacherEntity
import com.example.tcc.data.db.entities.TeamEntity
import com.example.tcc.data.prefs.PreferencesDataSource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppApplication : Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        val preferencesDataSource = PreferencesDataSource()

        if (!preferencesDataSource.getBoolean(PreferencesDataSource.ITEMS_CREATED_ON_TABLES)) {
            CoroutineScope(Dispatchers.IO).launch {
                with(AppDataBase.getInstance()) {
                    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

                    // Competition
                    val competitionId = competitionDAO().insert(CompetitionEntity())

                    // Teams
                    val papaleguasTeamId = teamDAO().insert(
                        TeamEntity(
                            name = "Papaléguas",
                            competitionId = competitionId
                        )
                    )
                    val twisterTeamId = teamDAO().insert(
                        TeamEntity(
                            name = "Twister",
                            competitionId = competitionId
                        )
                    )

                    // Courses
                    val itCourseId = courseDAO().insert(
                        CourseEntity(
                            name = "Informática",
                            teamId = papaleguasTeamId
                        )
                    )
                    val admCourseId = courseDAO().insert(
                        CourseEntity(
                            name = "Administração",
                            teamId = twisterTeamId
                        )
                    )

                    // Goals
                    val firstPapaleguasTeamGoalId = goalDAO().insert(
                        GoalEntity(
                            description = "teste meta equipe papaleguas",
                            quantityAchieved = 1.0,
                            totalQuantity = 10.0,
                            teamId = papaleguasTeamId
                        )
                    )

                    val firstTwisterTeamGoalId = goalDAO().insert(
                        GoalEntity(
                            description = "teste meta equipe twister",
                            quantityAchieved = 0.0,
                            totalQuantity = 15.0,
                            teamId = twisterTeamId
                        )
                    )

                    // Classes
                    val firstClassId = classDAO().insert(
                        ClassEntity(
                            name = "1-11",
                            courseId = admCourseId
                        )
                    )

                    val secondClassId = classDAO().insert(
                        ClassEntity(
                            name = "1-52",
                            courseId = itCourseId
                        )
                    )

                    // Students
                    val firstStudent = studentDAO().insert(
                        StudentEntity(
                            name = "Lucas Moro",
                            registrationNumber = "1234567890",
                            birthdate = sdf.parse("12-04-2003")!!,
                            password = "12042003",
                            email = "lucas@gmail.com",
                            classId = firstClassId
                        )
                    )
                    val secondStudent = studentDAO().insert(
                        StudentEntity(
                            name = "Ivan",
                            registrationNumber = "2345678901",
                            birthdate = sdf.parse("01-01-2007")!!,
                            password = "01012007",
                            email = "ivan@gmail.com",
                            classId = secondClassId
                        )
                    )

                    // Teachers
                    val firstTeacherId = teacherDAO().insert(
                        TeacherEntity(
                            name = "João",
                            email = "joao@gmail.com",
                            cellphone = "551231231231",
                            password = "1234",
                            socialNumber = "12345678901",
                            teamId = papaleguasTeamId
                        )
                    )
                    val secondTeacherId = teacherDAO().insert(
                        TeacherEntity(
                            name = "Maria",
                            email = "maria@gmail.com",
                            cellphone = "55123412341234",
                            password = "123456",
                            socialNumber = "012345678911",
                            teamId = twisterTeamId
                        )
                    )

                    // Disciplines
                    val mathDisciplineId = disciplineDAO().insert(
                        DisciplineEntity(
                            name = "Matemática",
                            description = "cálculos muito loucos"
                        )
                    )
                    val biologyDisciplineId = disciplineDAO().insert(
                        DisciplineEntity(
                            name = "Biologia",
                            description = "bixo muito loco"
                        )
                    )

                    // Activites
                    for (i in 0..50) {
                        activityDAO().insert(
                            ActivityEntity(
                                title = "Prova de matemática $i",
                                date = Date().resetTime(),
                                description = "prova de matemática description",
                                disciplineId = mathDisciplineId
                            )
                        )
                    }
                    for (i in 0..50) {
                        activityDAO().insert(
                            ActivityEntity(
                                title = "Prova de biologia $i",
                                date = Date().resetTime(),
                                description = "prova de biologia description",
                                disciplineId = biologyDisciplineId
                            )
                        )
                    }

                    disciplineDAO().insert(
                        DisciplineTeacherCrossRef(
                            disciplineId = mathDisciplineId,
                            teacherId = firstTeacherId
                        )
                    )
                    disciplineDAO().insert(
                        DisciplineTeacherCrossRef(
                            disciplineId = biologyDisciplineId,
                            teacherId = firstTeacherId
                        )
                    )
                    disciplineDAO().insert(
                        DisciplineTeacherCrossRef(
                            disciplineId = biologyDisciplineId,
                            teacherId = secondTeacherId
                        )
                    )
                }
            }
            preferencesDataSource.putBoolean(PreferencesDataSource.ITEMS_CREATED_ON_TABLES, true)
        }
    }

    companion object {
        private var instance: AppApplication? = null

        fun getInstance(): Context = instance!!.applicationContext
    }
}