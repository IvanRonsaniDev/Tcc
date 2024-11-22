package com.example.tcc.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.tcc.core.extensions.resetTime
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.databinding.FragmentHomeBinding
import com.example.tcc.AddActivityActivity
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.presentation.adapter.activity.ActivityAdapter
import java.util.Calendar
import java.util.Date
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val activitiesAdapter = ActivityAdapter {
        val intent = Intent(requireContext(), AddActivityActivity::class.java)
        intent.putExtra(AddActivityActivity.SELECTED_ACTIVITY, it)
        intent.putExtra(AddActivityActivity.SELECTED_DATE, currentDate)
        startActivity(intent)
    }
    private var currentDate = Calendar.getInstance().time.resetTime()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            val activities = emptyList<ActivityEntity>().toMutableList()
            val date = Calendar.getInstance().time.resetTime()
            if (AppSingleton.isTeacher) {
                AppDataBase.getInstance(requireContext()).teacherDAO()
                    .getTeacherWithDisciplines(AppSingleton.userId).disciplines.forEach {
                        activities += AppDataBase.getInstance(requireContext()).activityDAO()
                            .getActivitiesByDisciplineIdAndDate(it.disciplineId, date)
                    }
            } else {
                val studentClassId = AppDataBase.getInstance(requireContext()).studentDAO()
                    .getStudentBy(AppSingleton.userId).classId

                activities += AppDataBase.getInstance(requireContext()).activityDAO()
                    .getActivitiesByClassIdAndDate(studentClassId, date)
            }
            activitiesAdapter.activities = activities
            withContext(Dispatchers.Main) {
                activitiesAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            calendar.minDate = Calendar.getInstance().apply {
                set(Calendar.MONTH, 0)
                set(Calendar.DAY_OF_MONTH, 1)
            }.timeInMillis
            calendar.maxDate = Calendar.getInstance().apply {
                set(Calendar.MONTH, 11)
                set(Calendar.DAY_OF_MONTH, 31)
            }.timeInMillis
            rvActivites.adapter = activitiesAdapter

            calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
                Log.d("HomeFragment", "year: $year, month: $month, dayOfMonth: $dayOfMonth")
                currentDate = Calendar.getInstance().apply {
                    set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    set(Calendar.MONTH, month)
                    set(Calendar.YEAR, year)
                }.time.resetTime()
                CoroutineScope(Dispatchers.IO).launch {
                    val activities = emptyList<ActivityEntity>().toMutableList()
                    if (AppSingleton.isTeacher) {
                        AppDataBase.getInstance(requireContext()).teacherDAO()
                            .getTeacherWithDisciplines(AppSingleton.userId).disciplines.forEach {
                                activities += AppDataBase.getInstance(requireContext())
                                    .activityDAO()
                                    .getActivitiesByDisciplineIdAndDate(
                                        it.disciplineId,
                                        currentDate
                                    )
                            }
                    } else {
                        val studentClassId = AppDataBase.getInstance(requireContext()).studentDAO()
                            .getStudentBy(AppSingleton.userId).classId

                        activities += AppDataBase.getInstance(requireContext()).activityDAO()
                            .getActivitiesByClassIdAndDate(studentClassId, currentDate)
                    }

                    activitiesAdapter.activities = activities
                    withContext(Dispatchers.Main) {
                        activitiesAdapter.notifyDataSetChanged()
                    }
                }
            }
            btAddActivity.isVisible = AppSingleton.isTeacher
            btAddActivity.setOnClickListener {
                val intent = Intent(requireContext(), AddActivityActivity::class.java)
                intent.putExtra(AddActivityActivity.SELECTED_DATE, currentDate)
                startActivity(intent)
            }
        }
    }

}