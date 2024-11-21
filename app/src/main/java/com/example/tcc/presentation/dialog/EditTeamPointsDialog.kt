package com.example.tcc.presentation.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle

import com.example.tcc.databinding.DialogEditTeamPointsBinding

class EditTeamPointsDialog(
    context: Context,
    private val teamName: String,
    private val teamPoints: Int,
    private val onChangePoints: Dialog.(Int) -> Unit
) : Dialog(context) {

    private val binding = DialogEditTeamPointsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        with(binding) {
            tvTeamName.text = "Equipe: $teamName"
            etPoints.setText(teamPoints.toString())
            btnChange.setOnClickListener {
                onChangePoints(etPoints.text.toString().toInt())
            }
        }
    }

}