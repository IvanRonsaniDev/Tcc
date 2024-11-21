package com.example.tcc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tcc.databinding.FragmentNotesStudentsBinding

class fragment_notes_students : Fragment() {
    private lateinit var binding: FragmentNotesStudentsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotesStudentsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_notes_students, container, false)
    }

            }
