package com.example.tcc.data.repository

import com.example.tcc.AppApplication
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.dao.ActivityDAO
import com.example.tcc.data.db.dao.GoalDAO
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.data.db.entities.GoalEntity

class ActivityRepository(
    private val actvityDAO: ActivityDAO = AppDataBase.getInstance(AppApplication.getInstance()).activityDAO()
) {


    suspend fun deleteActivity(activity: ActivityEntity) {
        actvityDAO.delete(activity)
    }
}