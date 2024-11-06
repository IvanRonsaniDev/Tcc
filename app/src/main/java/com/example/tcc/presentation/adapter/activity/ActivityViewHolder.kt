package com.example.tcc.presentation.adapter.activity

import androidx.recyclerview.widget.RecyclerView
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.databinding.VhActivityBinding

class ActivityViewHolder(private val binding: VhActivityBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(activity: ActivityEntity, onClick: (ActivityEntity) -> Unit) = with(binding) {
        textView.text = activity.title
        root.setOnClickListener {
            onClick(activity)
        }
    }

}