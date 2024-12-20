package com.example.tcc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.databinding.VhGoalBinding

class GoalAdapter(private val onClick: (GoalEntity) -> Unit) :
    RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    private var goals: List<GoalEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val binding = VhGoalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        holder.bind(goals[position])
    }

    override fun getItemCount(): Int = goals.size

    fun submitList(newGoals: List<GoalEntity>) {
        goals = newGoals
        notifyDataSetChanged()
    }

    inner class GoalViewHolder(private val binding: VhGoalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(goal: GoalEntity) {
            binding.root.setOnClickListener {
                onClick(goal)
            }
            binding.tvGoalName.text = goal.name
            binding.tvGoalDescription.text = goal.description
            val progress = if (goal.totalQuantity > 0) {
                (goal.quantityAchieved / goal.totalQuantity * 100).toInt()
            } else {
                0
            }
            binding.tvGoalProgress.text = "$progress%"
            binding.progressBar.setProgress(progress, true)
            binding.tvDisclaimer.text = if (AppSingleton.isTeacher) "Editar" else "Saiba mais"
        }
    }
}
