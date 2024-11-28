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

                    val uniconttiTeamId = teamDAO().insert(
                        TeamEntity(
                            name = "Unicontti",
                            competitionId = competitionId,
                            instagramAt = "equipeunicontti"
                        )
                    )

                        val atomicaTeamId = teamDAO().insert(
                        TeamEntity(
                            name = "Atômica",
                            competitionId = competitionId,
                            instagramAt = "equipeatomica"
                        )
                        )

                        val mestresTeamId = teamDAO().insert(
                        TeamEntity(
                            name = "Mestres",
                            competitionId = competitionId,
                            instagramAt = "equipemestredeobras"
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
                    val ediCourseId = courseDAO().insert(
                        CourseEntity(
                            name = "Edificações",
                            teamId = mestresTeamId
                        )
                    )
                    val quiCourseId = courseDAO().insert(
                        CourseEntity(
                            name = "Química",
                            teamId = atomicaTeamId
                        )
                    )
                    val mktCourseId = courseDAO().insert(
                        CourseEntity(
                            name = "Marketing",
                            teamId = uniconttiTeamId
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
                    val thirdClassId = classDAO().insert(
                        ClassEntity(
                            name = "1-41",
                            courseId = ediCourseId
                        )
                    )
                    val fourthClassId = classDAO().insert(
                        ClassEntity(
                            name = "1-61",
                            courseId = quiCourseId
                        )
                    )
                    val fifthClassId = classDAO().insert(
                        ClassEntity(
                            name = "1-81",
                            courseId = mktCourseId
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
                    val thirdStudent = studentDAO().insert(
                        StudentEntity(
                            name = "Lucas Cunhaski",
                            registrationNumber = "1234567890",
                            birthdate = sdf.parse("01-01-2007")!!,
                            password = "01012007",
                            email = "lucas@gmail.com",
                            classId = thirdClassId
                        )
                    )
                    val fourthStudent = studentDAO().insert(
                        StudentEntity(
                            name = "Brenda Beltrao",
                            registrationNumber = "1234567890",
                            birthdate = sdf.parse("14-06-2008")!!,
                            password = "14062007",
                            email = "brenda@gmail.com",
                            classId = fourthClassId
                        )
                    )
                    val fifthStudent = studentDAO().insert(
                        StudentEntity(
                            name = "Neymar Junior",
                            registrationNumber = "1234567890",
                            birthdate = sdf.parse("21-02-1995")!!,
                            password = "21021995",
                            email = "neymar@gmail.com",
                            classId = fifthClassId
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
                    val thirdTeacherId = teacherDAO().insert(
                        TeacherEntity(
                            name = "Gabriela",
                            email = "gabriela@gmail.com",
                            cellphone = "554898123456",
                            password = "2222",
                            socialNumber = "98765432109",
                            teamId = atomicaTeamId
                        )
                    )
                    val fourthTeacherId = teacherDAO().insert(
                        TeacherEntity(
                            name = "Fernando",
                            email = "fernando@gmail.com",
                            cellphone = "554899876543",
                            password = "0000",
                            socialNumber = "65432198701",
                            teamId = mestresTeamId
                        )
                    )
                    val fifthTeacherId = teacherDAO().insert(
                        TeacherEntity(
                            name = "Marina",
                            email = "marina@gmail.com",
                            cellphone = "554897654321",
                            password = "1111",
                            socialNumber = "11223344556",
                            teamId = uniconttiTeamId
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
                    val physicsDisciplineId = disciplineDAO().insert(
                        DisciplineEntity(
                            name = "Física",
                            description = "Leis do universo e experimentos incríveis"
                        )
                    )
                    val chemistryDisciplineId = disciplineDAO().insert(
                        DisciplineEntity(
                            name = "Química",
                            description = "Misturas, reações e elementos químicos"
                        )
                    )
                    val historyDisciplineId = disciplineDAO().insert(
                        DisciplineEntity(
                            name = "História",
                            description = "Estudos sobre o passado e suas lições"
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
                            disciplineId = physicsDisciplineId,
                            teacherId = thirdTeacherId
                        )
                    )
                    disciplineDAO().insert(
                        DisciplineTeacherCrossRef(
                            disciplineId = chemistryDisciplineId,
                            teacherId = fourthTeacherId
                        )
                    )
                    disciplineDAO().insert(
                        DisciplineTeacherCrossRef(
                            disciplineId = historyDisciplineId,
                            teacherId = fifthTeacherId
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
                            classId = thirdClassId, // Turma 1-41
                            teacherId = thirdTeacherId
                        )
                    )
                    teacherDAO().insert(
                        ClassTeacherCrossRef(
                            classId = fourthClassId, // Turma 1-61
                            teacherId = fourthTeacherId
                        )
                    )
                    teacherDAO().insert(
                        ClassTeacherCrossRef(
                            classId = fifthClassId, // Turma 1-81
                            teacherId = fifthTeacherId
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