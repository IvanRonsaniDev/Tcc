package com.example.tcc.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.example.tcc.databinding.DialogEditNotesBinding


    class EditNotesDialog(
        context: Context,
        private val name: String,
        private val noteStudents: Double,
        private val onChangePoints: Dialog.(Int) -> Unit
    ) : Dialog(context) {

        private val binding = DialogEditNotesBinding.inflate(layoutInflater)

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)
            with(binding) {
                tvStudentName.text = "Aluno: $name"
                etNotes.setText(noteStudents.toString())
                btnAdd.setOnClickListener {
                    onChangePoints(etNotes.text.toString().toInt())
                }
            }
        }

    }
