package com.example.tcc.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tcc.AppApplication
import com.example.tcc.data.db.converters.Converters
import com.example.tcc.data.db.dao.ActivityDAO
import com.example.tcc.data.db.dao.ClassDAO
import com.example.tcc.data.db.dao.CompetitionDAO
import com.example.tcc.data.db.dao.CourseDAO
import com.example.tcc.data.db.dao.DisciplineDAO
import com.example.tcc.data.db.dao.GoalDAO
import com.example.tcc.data.db.dao.GradeDAO
import com.example.tcc.data.db.dao.StudentDAO
import com.example.tcc.data.db.dao.TeacherDAO
import com.example.tcc.data.db.dao.TeamDAO
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.data.db.entities.ClassEntity
import com.example.tcc.data.db.entities.CompetitionEntity
import com.example.tcc.data.db.entities.CourseEntity
import com.example.tcc.data.db.entities.DisciplineEntity
import com.example.tcc.data.db.entities.DisciplineTeacherCrossRef
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.data.db.entities.GradeEntity
import com.example.tcc.data.db.entities.StudentEntity
import com.example.tcc.data.db.entities.TeacherEntity
import com.example.tcc.data.db.entities.TeamEntity

@Database(
    entities = [ClassEntity::class, CompetitionEntity::class, CourseEntity::class, GoalEntity::class, StudentEntity::class, TeacherEntity::class, TeamEntity::class, ActivityEntity::class, DisciplineEntity::class, DisciplineTeacherCrossRef::class, GradeEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun activityDAO(): ActivityDAO
    abstract fun disciplineDAO(): DisciplineDAO
    abstract fun gradeDAO(): GradeDAO
    abstract fun classDAO(): ClassDAO
    abstract fun competitionDAO(): CompetitionDAO
    abstract fun courseDAO(): CourseDAO
    abstract fun goalDAO(): GoalDAO
    abstract fun studentDAO(): StudentDAO
    abstract fun teacherDAO(): TeacherDAO
    abstract fun teamDAO(): TeamDAO

    companion object {
        private var roomDB: AppDataBase? = null

        fun getInstance(): AppDataBase =
            synchronized(this) {
                if (roomDB == null) {
                    roomDB = Room.databaseBuilder(
                        AppApplication.getInstance(),
                        AppDataBase::class.java,
                        "tcc-db"
                    ).build()
                }
                return roomDB!!
            }
    }
}