package com.example.tcc.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.tcc.R
import com.example.tcc.core.extensions.resetTime
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.databinding.FragmentHomeBinding
import com.example.tcc.presentation.adapter.activity.ActivityAdapter
import java.util.Calendar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val activitiesAdapter = ActivityAdapter {
        Log.d("HomeFragment", it.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
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
            CoroutineScope(Dispatchers.IO).launch {
                activitiesAdapter.activities = AppDataBase.getInstance().activityDAO().getAll(
                    Calendar.getInstance().time.resetTime()
                )
                withContext(Dispatchers.Main) {
                    activitiesAdapter.notifyDataSetChanged()
                }
            }

            calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
                Log.d("HomeFragment", "year: $year, month: $month, dayOfMonth: $dayOfMonth")
                CoroutineScope(Dispatchers.IO).launch {
                    activitiesAdapter.activities =
                        AppDataBase.getInstance().activityDAO().getAll(
                            Calendar.getInstance().apply {
                                set(Calendar.DAY_OF_MONTH, dayOfMonth)
                                set(Calendar.MONTH, month)
                                set(Calendar.YEAR, year)
                            }.time.resetTime()
                    )
                    withContext(Dispatchers.Main) {
                        activitiesAdapter.notifyDataSetChanged()
                    }
                }
            }

            btAddActivity.isVisible = AppSingleton.isTeacher
            btAddActivity.setOnClickListener {
                Log.d("HomeFragment", "onAddActivity")
            }
        }
    }

}