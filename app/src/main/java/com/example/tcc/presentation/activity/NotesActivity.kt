package com.example.tcc.presentation.activity

import android.os.Bundle
import android.view.Gravity
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.tcc.core.base.BaseActivity
import com.example.tcc.core.extensions.showPopupMenu
import com.example.tcc.core.extensions.showToast
import com.example.tcc.data.AppSingleton
import com.example.tcc.databinding.ActivityNotesBinding
import com.example.tcc.presentation.adapter.NotesAdapter
import com.example.tcc.presentation.dialog.EditNotesDialog
import com.example.tcc.presentation.viewModel.NotesViewModel


class NotesActivity : BaseActivity() {

    private lateinit var binding: ActivityNotesBinding
    private val viewModel: NotesViewModel by viewModels()
    private val notesAdapter = NotesAdapter {
        if (AppSingleton.isTeacher.not()) return@NotesAdapter
        EditNotesDialog(
            context = this,
            name = it.student.name,
            noteStudents = it.note?.note ?: 0.0,
        ) { newNotes ->
            viewModel.updateNote(it, newNotes)
            dismiss()
            showToast("NOTA ALTERADA")
        }.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupListeners()
        setupObservers()
        viewModel.fetchData(disciplineId = intent.extras?.getLong(DISCIPLINE_ID) ?: 0)
    }

    private fun setupViews() = with(binding) {
        etTurma.isVisible = AppSingleton.isTeacher
        rvNotes.adapter = notesAdapter
    }

    private fun setupListeners() = with(binding) {
        etTurma.setOnClickListener {
            showPopupMenu(
                anchorView = binding.etTurma,
                items = viewModel.classes
            ) {
                viewModel.onSelectClass(it.first)
                binding.etTurma.setText(it.second)
            }
        }
        etAtividade.setOnClickListener {
            showPopupMenu(
                anchorView = binding.etAtividade,
                items = viewModel.activities
            ) {
                viewModel.onSelectActivity(it.first)
                binding.etAtividade.setText(it.second)
            }
        }
    }

    private fun setupObservers() = with(viewModel) {
        students.observe(this@NotesActivity) {
            notesAdapter.submitList(it)
        }
        onGetActivities.observe(this@NotesActivity) {
            binding.etAtividade.setText(selectedActivity)
        }
        onGetClasses.observe(this@NotesActivity) {
            binding.etTurma.setText(selectedClass)
        }
    }

    companion object {
        const val DISCIPLINE_ID = "DISCIPLINE_ID"
    }
}