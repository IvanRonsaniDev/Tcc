package com.example.tcc.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.databinding.FragmentPerfilBinding
import com.example.tcc.presentation.activity.ChangePasswordActivity
import com.example.tcc.presentation.viewModel.ProfileViewModel
import com.example.tcc.presentation.viewModel.ProfileViewModelFactory

class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private val profileViewModel: ProfileViewModel by viewModels {
        val db = AppDataBase.getInstance(requireContext())
        ProfileViewModelFactory(
            db.studentDAO(),
            db.teacherDAO(),
            db.classDAO(),
            db.courseDAO(),
            db.teamDAO()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.loadUserName()

        profileViewModel.userName.observe(viewLifecycleOwner) { name ->
            binding.perfilNome.text = name
        }

        profileViewModel.nameInitials.observe(viewLifecycleOwner) { nameInitials ->
            binding.tvIniciaisNome.text = nameInitials
        }

        profileViewModel.classTeam.observe(viewLifecycleOwner) {
            binding.perfilTurma.text = it
        }

        profileViewModel.studentRegistrationNumber.observe(viewLifecycleOwner) {
            binding.perfilMatricula.text = it
            binding.perfilMatricula.isVisible = !it.isNullOrBlank()
        }

        binding.btnChangePassword.setOnClickListener {
            val intent = Intent(context, ChangePasswordActivity::class.java)
            startActivity(intent)
        }


        binding.btnLogout.setOnClickListener {
            activity?.finish()
        }
    }
}
