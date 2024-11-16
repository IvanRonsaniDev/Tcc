package com.example.tcc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.tcc.R
import com.example.tcc.data.db.entities.TeamEntity
import com.example.tcc.databinding.VhTableBinding

class TableAdapter(
    private val onClick: (TeamEntity) -> Unit
) : RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    private var teams: List<TeamEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val binding = VhTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(teams[position], position)
    }

    override fun getItemCount(): Int = teams.size

    fun submitList(teams: List<TeamEntity>) {
        this.teams = teams
        notifyDataSetChanged()
    }

    inner class TableViewHolder(private val binding: VhTableBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(team: TeamEntity, position: Int) = with(binding) {
            root.setOnClickListener {
                onClick(team)
            }
            tvPosition.setBackgroundResource(
                when (position) {
                    0 -> R.color.gold
                    1 -> R.color.silver
                    2 -> R.color.copper
                    else -> R.color.red
                }
            )
            vTopSeparator.isVisible = position == 0
            tvPosition.text = position.plus(1).toString()
            tvName.text = team.name
            tvPoints.text = team.points.toString()
        }
    }
}

