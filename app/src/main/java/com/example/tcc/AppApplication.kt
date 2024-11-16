package com.example.tcc

import android.app.Application
import android.content.Context
import com.example.tcc.core.extensions.resetTime
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.data.db.entities.ClassEntity
import com.example.tcc.data.db.entities.ClassTeacherCrossRef
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
                with(AppDataBase.getInstance(applicationContext)) {
                    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

                    // Competition
                    val competitionId = competitionDAO().insert(CompetitionEntity())

                    // Teams
                    val papaleguasTeamId = teamDAO().insert(
                        TeamEntity(
                            name = "Papaléguas",
                            competitionId = competitionId,
                            instagramAt = "papaleguas_cedup"
                        )
                    )
                    val twisterTeamId = teamDAO().insert(
                        TeamEntity(
                            name = "Twister",
                            competitionId = competitionId,
                            instagramAt = "equipe_twister"
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
                            name = "Meta 1",
                            description = "teste meta equipe papaleguas",
                            quantityAchieved = 1.0,
                            totalQuantity = 10.0,
                            teamId = papaleguasTeamId
                        )
                    )

                    val firstTwisterTeamGoalId = goalDAO().insert(
                        GoalEntity(
                            name = "Meta 2",
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
                            name = "Alessandro De Jesus",
                            registrationNumber = "1234567890",
                            birthdate = sdf.parse("29-08-2006")!!,
                            password = "29082006",
                            email = "alessandro@gmail.com",
                            classId = firstClassId
                        )
                    )
                    val secondStudent = studentDAO().insert(
                        StudentEntity(
                            name = "Ivan",
                            registrationNumber = "0123456789",
                            birthdate = sdf.parse("28-06-2006")!!,
                            password = "28062006",
                            email = "ivan@gmail.com",
                            classId = secondClassId
                        )
                    )

                    // Teachers
                    val firstTeacherId = teacherDAO().insert(
                        TeacherEntity(
                            name = "Evandro",
                            email = "evandro@gmail.com",
                            cellphone = "554896802417",
                            password = "1234",
                            socialNumber = "12345678901",
                            teamId = papaleguasTeamId
                        )
                    )
                    val secondTeacherId = teacherDAO().insert(
                        TeacherEntity(
                            name = "Eloy",
                            email = "eloy@gmail.com",
                            cellphone = "554898422362",
                            password = "123456",
                            socialNumber = "11717501966",
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
                    ActivityEntity(
                        title = "Prova de matemática ",
                        date = Date().resetTime(),
                        description = "prova de matemática description",
                        disciplineId = mathDisciplineId,
                        classId = firstClassId,
                        isEvaluative = false
                    )


                    ActivityEntity(
                        title = "Prova de biologia ",
                        date = Date().resetTime(),
                        description = "prova de biologia description",
                        disciplineId = biologyDisciplineId,
                        classId = secondClassId,
                        isEvaluative = true
                    )

                    disciplineDAO().insert(
                        DisciplineTeacherCrossRef(
                            disciplineId = mathDisciplineId,
                            teacherId = firstTeacherId
                        )
                    )
                    disciplineDAO().insert(
                        DisciplineTeacherCrossRef(
                            disciplineId = biologyDisciplineId,
                            teacherId = secondTeacherId
                        )
                    )

                    teacherDAO().insert(
                        ClassTeacherCrossRef(
                            classId = secondClassId,
                            teacherId = firstTeacherId
                        )
                    )
                    teacherDAO().insert(
                        ClassTeacherCrossRef(
                            classId = firstClassId,
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