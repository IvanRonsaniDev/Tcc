package com.example.tcc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.databinding.ItemGoalBinding

class GoalAdapter : RecyclerView.Adapter<GoalAdapter.GoalViewHolder>() {

    private var goals: List<GoalEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val binding = ItemGoalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    inner class GoalViewHolder(private val binding: ItemGoalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(goal: GoalEntity) {
            binding.tvGoalName.text = goal.name
            binding.tvGoalDescription.text = goal.description
            val progress = if (goal.totalQuantity > 0) {
                (goal.quantityAchieved.toDouble() / goal.totalQuantity * 100).toInt()
            } else {
                0
            }
            binding.tvGoalProgress.text = "Progresso: $progress%"
        }
    }
}
