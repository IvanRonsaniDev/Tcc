package com.example.tcc.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tcc.R
import com.example.tcc.databinding.FragmentHomeBinding
import java.util.Calendar

class HomeFragment : Fragment() {

 private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        calendar.minDate = Calendar.getInstance().apply {
            set(Calendar.MONTH,0)
            set(Calendar.DAY_OF_MONTH,1)
        }.timeInMillis
        calendar.maxDate = Calendar.getInstance().apply {
            set(Calendar.MONTH,11)
            set(Calendar.DAY_OF_MONTH,31)
        }.timeInMillis

    }


}