package com.example.tcc.presentation.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.tcc.core.base.BaseActivity
import com.example.tcc.core.extensions.showToast
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.databinding.ActivityFormGoalBinding
import com.example.tcc.presentation.dialog.ConfirmDeleteDialogFragment
import com.example.tcc.presentation.viewModel.GoalFormViewModel

class GoalFormActivity : BaseActivity() {

    private lateinit var binding: ActivityFormGoalBinding
    private val viewModel: GoalFormViewModel by viewModels()
    private var goal: GoalEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.fetchData()

        goal = intent.getSerializableExtra(SELECTED_GOAL) as? GoalEntity



        with(binding) {
            goal?.let {
                etGoalName.setText(goal?.name)
                etGoalDescription.setText(goal?.description)
                etGoalQuantityAchieved.setText(goal?.quantityAchieved.toString())
                etGoalQuantity.setText(goal?.totalQuantity.toString())
                textActual.text
                btnDelete.bottom
            }

            btnSaveGoal.text = if (goal != null) "Atualizar meta" else "Salvar meta"
            etGoalQuantityAchieved.isVisible = goal != null
            textActual.isVisible = goal != null
            btnDelete.isVisible = goal != null

            btnSaveGoal.setOnClickListener {
                val name = etGoalName.text.toString()
                val description = etGoalDescription.text.toString()
                val quantityAchieved = etGoalQuantityAchieved.text.toString()
                val totalQuantity = etGoalQuantity.text.toString().toDouble()

                val goal = GoalEntity(
                    id = this@GoalFormActivity.goal?.id ?: 0L,
                    teamId = viewModel.userTeamId,
                    name = name,
                    description = description,
                    totalQuantity = totalQuantity,
                    quantityAchieved = quantityAchieved.toDoubleOrNull() ?: 0.0,
                )

                if (this@GoalFormActivity.goal != null) {
                    showToast("Meta atualizada com sucesso!")
                } else {
                    showToast("Meta adicionada com sucesso!")
                }

                if (this@GoalFormActivity.goal != null) {
                    viewModel.updateGoal(goal)
                } else {
                    viewModel.addGoal(goal)
                }

                finish()
            }

            btnDelete.setOnClickListener {
                val dialog = ConfirmDeleteDialogFragment { isConfirmed ->
                    if (isConfirmed) {
                        goal?.let {
                            viewModel.deleteGoal(it)
                            showToast("Meta excluída com sucesso!")
                            finish()
                        }
                    }
                }
                dialog.show(supportFragmentManager, "ConfirmDeleteDialog")
            }


        }
    }

    companion object {
        const val SELECTED_GOAL = "SELECTED_GOAL"
    }
}