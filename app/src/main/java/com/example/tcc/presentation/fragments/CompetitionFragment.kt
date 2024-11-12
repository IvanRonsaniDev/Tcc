package com.example.tcc.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tcc.data.AppSingleton
import com.example.tcc.databinding.FragmentCompetitionBinding
import com.example.tcc.presentation.activity.AddGoalActivity
import com.example.tcc.presentation.adapter.GoalAdapter
import com.example.tcc.presentation.viewModel.CompetitionViewModel

class CompetitionFragment : Fragment() {

    private lateinit var binding: FragmentCompetitionBinding
    private val viewModel: CompetitionViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCompetitionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configura o RecyclerView
        with(binding) {
            val goalAdapter = GoalAdapter()
            rvGoals.adapter = goalAdapter

            // Observa as metas da equipe
            viewModel.goals.observe(viewLifecycleOwner) {
                goalAdapter.submitList(it)
            }

//            btnAddGoal.isVisible = AppSingleton.isTeacher
            // Ação para adicionar uma nova meta
            btnAddGoal.setOnClickListener {
                val intent = Intent(context, AddGoalActivity::class.java)
                startActivity(intent)
                // está pegando esse botao :)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getGoals()
    }
}
