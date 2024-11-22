package com.example.tcc.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tcc.data.db.entities.DisciplineEntity
import com.example.tcc.databinding.VhDisciplineBinding

class DisciplineAdapter(
    private val onClick: (DisciplineEntity) -> Unit
) : RecyclerView.Adapter<DisciplineAdapter.DisciplineViewHolder>() {

    private var disciplines: List<DisciplineEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisciplineViewHolder {
        val binding =
            VhDisciplineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DisciplineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DisciplineViewHolder, position: Int) {
        holder.bind(disciplines[position])
    }

    override fun getItemCount(): Int = disciplines.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(students: List<DisciplineEntity>) {
        this.disciplines = students
        notifyDataSetChanged()
    }

    inner class DisciplineViewHolder(private val binding: VhDisciplineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(discipline: DisciplineEntity) = with(binding) {
            root.setOnClickListener {
                onClick(discipline)
            }
            tvDiscipline.text = discipline.name
        }
    }
}