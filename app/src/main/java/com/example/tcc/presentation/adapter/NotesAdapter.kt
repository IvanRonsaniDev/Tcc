package com.example.tcc.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tcc.data.db.entities.StudentWithNote
import com.example.tcc.databinding.VhNoteBinding

class NotesAdapter(
    private val onClick: (StudentWithNote) -> Unit
) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private var students: List<StudentWithNote> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = VhNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(students: List<StudentWithNote>) {
        this.students = students
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: VhNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(student: StudentWithNote) = with(binding) {
            root.setOnClickListener {
                onClick(student)
            }
            tvStudentName.text = student.student.name
            tvStudentGrade.text = (student.note?.note ?: 0.0).toString()
        }
    }
}