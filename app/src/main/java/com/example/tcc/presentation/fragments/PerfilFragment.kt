package com.example.tcc.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.databinding.FragmentPerfilBinding
import com.example.tcc.presentation.viewModel.ProfileViewModel
import com.example.tcc.presentation.viewModel.ProfileViewModelFactory

class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private val profileViewModel: ProfileViewModel by viewModels {
        val db = AppDataBase.getInstance(requireContext())
        ProfileViewModelFactory(db.studentDAO(), db.teacherDAO())
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

        binding.btnLogout.setOnClickListener {
            activity?.finish()
        }
    }
}
