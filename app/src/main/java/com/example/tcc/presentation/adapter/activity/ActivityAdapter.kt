package com.example.tcc.presentation.adapter.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.databinding.VhActivityBinding

class ActivityAdapter(
    private val onClick: (ActivityEntity) -> Unit
) : RecyclerView.Adapter<ActivityViewHolder>() {

    var activities: List<ActivityEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder =
        ActivityViewHolder(
            VhActivityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = activities.size

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.onBind(activities[position], onClick)
    }
}