package com.example.tcc.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tcc.R
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.repository.GoalRepository
import com.example.tcc.presentation.adapter.GoalAdapter
import com.example.tcc.presentation.viewmodel.GoalViewModel
import com.example.tcc.presentation.viewmodel.GoalViewModelFactory
import kotlinx.android.synthetic.main.fragment_goals.*

class GoalsFragment : Fragment() {

    private lateinit var goalViewModel: GoalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_goals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicialize o ViewModel
        val goalRepository = GoalRepository(AppDataBase.getInstance(requireContext()).goalDAO())
        goalViewModel = ViewModelProvider(this, GoalViewModelFactory(goalRepository)).get(GoalViewModel::class.java)

        // Configura o RecyclerView
        val goalAdapter = GoalAdapter()
        rvGoals.layoutManager = LinearLayoutManager(requireContext())
        rvGoals.adapter = goalAdapter

        // Observa as metas da equipe
        val teamId = 1  // ID da equipe do professor
        goalViewModel.getGoalsForTeam(teamId).observe(viewLifecycleOwner, Observer { goals ->
            goals?.let {
                goalAdapter.submitList(it)
            }
        })

        // Ação para adicionar uma nova meta
        btnAddGoal.setOnClickListener {
            // n ta pegando esse botao :(
        }
    }
}
