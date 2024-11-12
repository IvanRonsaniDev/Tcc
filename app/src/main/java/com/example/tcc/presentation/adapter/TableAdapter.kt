package com.example.tcc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tcc.data.db.entities.CompetitionEntity
import com.example.tcc.databinding.VhTableBinding


class TableAdapter: RecyclerView.Adapter<TableAdapter.TableViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val binding = VhTableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TableViewHolder(binding)


        class TableViewHolder(private val binding: VhTableBinding) :
            RecyclerView.ViewHolder(binding.root) {

                //n sei oq pegar da gincana??
        }


        }
        }

