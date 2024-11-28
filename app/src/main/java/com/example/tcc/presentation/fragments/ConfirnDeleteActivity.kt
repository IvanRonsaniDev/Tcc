package com.example.tcc.presentation.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.tcc.databinding.DialogConfirmDeleteBinding

class ConfirmDeleteActivityDialogFragment(private val onConfirm: (Boolean) -> Unit) : DialogFragment() {

    private var _binding: DialogConfirmDeleteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DialogConfirmDeleteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnYes.setOnClickListener {
            onConfirm(true)
            dismiss()
        }


        binding.btnNo.setOnClickListener {
            onConfirm(false)
            dismiss()
        }
    }


}

