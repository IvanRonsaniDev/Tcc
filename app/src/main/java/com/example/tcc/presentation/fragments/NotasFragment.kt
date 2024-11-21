package com.example.tcc.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.example.tcc.R
import com.example.tcc.core.extensions.showToast
import com.example.tcc.data.AppSingleton
import com.example.tcc.databinding.FragmentCompetitionBinding

import com.example.tcc.databinding.FragmentNotasBinding
import com.example.tcc.presentation.adapter.NoteAdapter
import com.example.tcc.presentation.adapter.TableAdapter
import com.example.tcc.presentation.dialog.EditNotesDialog
import com.example.tcc.presentation.dialog.EditTeamPointsDialog
import com.example.tcc.presentation.viewModel.NotesViewModel


class NotasFragment : Fragment() {

    private lateinit var binding:FragmentNotasBinding
    private val viewModel: NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotasBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //configura a recycler
        //fazer um adapter
        with(binding) {
            val noteAdapter = NoteAdapter {
                if (AppSingleton.isTeacher.not()) return@NoteAdapter
                EditNotesDialog(
                    context = requireContext(),
                     name = it.name,
                    noteStudents = it.notes,
                ) { newNotes ->
                    viewModel.updateNote(it.copy(note = newNotes))
                    dismiss()
                    showToast("NOTA ALTERADA")
                }.show()
}
            rvNotes.adapter = noteAdapter