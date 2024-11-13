package com.example.tcc.presentation.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.net.toUri
import com.example.tcc.core.base.BaseActivity
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.databinding.ActivityGoalInfosBinding
import com.example.tcc.presentation.viewModel.GoalInfosViewModel

class GoalInfosActivity : BaseActivity() {

    private lateinit var binding: ActivityGoalInfosBinding
    private val viewModel by viewModels<GoalInfosViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoalInfosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.fetchData()
        val goal = intent.extras?.getSerializable(GOAL) as? GoalEntity
        val progress = if ((goal?.totalQuantity ?: 0.0) > 0) {
            ((goal?.quantityAchieved ?: 0.0) / (goal?.totalQuantity ?: 0.0) * 100).toInt()
        } else {
            0
        }

        viewModel.teamInstagramAt.observe(this) {
            binding.tvInstagramAt.text = "@$it"
        }

        with(binding) {
            tvGoalName.text = goal?.name
            tvQuantityAchieved.text = goal?.quantityAchieved?.toString()
            binding.tvGoalProgress.text = "$progress%"
            binding.progressBar.setProgress(progress, true)
            tvGoalDescription.text = goal?.description
            btnWhatsApp.setOnClickListener {
                val uri =
                    "https://api.whatsapp.com/send?phone=${viewModel.teacherCellphone}".toUri()
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            btnEmail.setOnClickListener {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${viewModel.teacherEmail}")
                }
                startActivity(Intent.createChooser(emailIntent, "Email via…"))
            }
            btnInstagram.setOnClickListener {
                val teamInstagramAt = viewModel.teamInstagramAt.value
                val uri = Uri.parse("http://instagram.com/_u/$teamInstagramAt")
                val instagramAppIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                    `package` = "com.instagram.android"
                }
                try {
                    startActivity(instagramAppIntent)
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/$teamInstagramAt")
                        )
                    )
                }
            }
        }
    }

    companion object {
        const val GOAL = "GOAL"
    }
}