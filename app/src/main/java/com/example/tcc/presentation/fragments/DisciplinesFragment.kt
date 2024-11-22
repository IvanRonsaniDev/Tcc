package com.example.tcc.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tcc.databinding.FragmentDisciplinesBinding
import com.example.tcc.presentation.activity.NotesActivity
import com.example.tcc.presentation.adapter.DisciplineAdapter
import com.example.tcc.presentation.viewModel.DisciplinesViewModel

class DisciplinesFragment: Fragment() {

    private lateinit var binding: FragmentDisciplinesBinding
    private val viewModel: DisciplinesViewModel by viewModels()
    private val disciplinesAdapter = DisciplineAdapter {
        val intent = Intent(requireContext(), NotesActivity::class.java).apply {
            putExtra(NotesActivity.DISCIPLINE_ID, it.disciplineId)
        }
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDisciplinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvDisciplines.adapter = disciplinesAdapter
        viewModel.getDisciplines()
        viewModel.disciplines.observe(viewLifecycleOwner) {
            disciplinesAdapter.submitList(it)
        }
    }
}