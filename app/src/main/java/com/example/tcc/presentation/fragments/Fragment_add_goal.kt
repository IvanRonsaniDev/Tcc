package com.example.tcc.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tcc.R
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.presentation.viewmodel.GoalViewModel
import kotlinx.android.synthetic.main.fragment_add_goal.*

class AddGoalFragment : Fragment() {

    private val goalViewModel: GoalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_goal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSaveGoal.setOnClickListener {
            val name = etGoalName.text.toString()
            val description = etGoalDescription.text.toString()
            val targetQuantity = etTargetQuantity.text.toString().toInt()

            val goal = GoalEntity(
                teamId = 1, // se for eqp 1
                name = name,
                description = description,
                targetQuantity = targetQuantity
            )

            goalViewModel.addGoal(goal)

            findNavController().navigateUp()
        }
    }
}
