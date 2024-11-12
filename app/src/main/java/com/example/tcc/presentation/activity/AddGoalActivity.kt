package com.example.tcc.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.example.tcc.core.base.BaseActivity
import com.example.tcc.core.extensions.showToast
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.databinding.ActivityAddGoalBinding
import com.example.tcc.presentation.viewModel.AddGoalViewModel

class AddGoalActivity : BaseActivity() {

    private lateinit var binding: ActivityAddGoalBinding
    private val viewModel: AddGoalViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.fetchData()

        with(binding) {
            btnSaveGoal.setOnClickListener {
                val name = etGoalName.text.toString()
                val description = etGoalDescription.text.toString()
                val totalQuantity = etGoalQuantity.text.toString().toDouble()

                val goal = GoalEntity(
                    teamId = viewModel.userTeamId,
                    name = name,
                    description = description,
                    totalQuantity = totalQuantity,
                    quantityAchieved = 10.0,
                )

                showToast("Meta adicionada com sucesso!")

                viewModel.addGoal(goal)

                finish()
            }
        }
    }
}