package com.example.tcc.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tcc.data.db.entities.StudentEntity

import com.example.tcc.databinding.VhNoteBinding


class NoteAdapter {

    private val onClick: (StudentEntity) -> Unit
    ) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

        private var students: List<StudentEntity>
        private var note: List<StudentEntity> = listOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
            val binding = VhNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return NoteViewHolder(binding)
        }

        override fun onBindViewHolder(holder: NoteViewHolder, note: Double) {
            holder.bind(students[note], note)
        }

        override fun getItemCount(): Int = students.size

        fun submitList(students: List<StudentEntity>) {
            this.students = students
            notifyDataSetChanged()
        }

    class NoteViewHolder(private val binding: VhNoteBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(student: StudentEntity, note: Double) = with(binding) {
                root.setOnClickListener {
                    onClick(students)
                }
        }
            }