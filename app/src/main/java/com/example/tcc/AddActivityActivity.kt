package com.example.tcc

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.viewModelScope
import com.example.tcc.core.base.BaseActivity
import com.example.tcc.core.extensions.showPopupMenu
import com.example.tcc.core.extensions.showToast
import com.example.tcc.data.AppSingleton
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.data.db.entities.ClassEntity
import com.example.tcc.data.db.entities.DisciplineEntity
import com.example.tcc.data.db.entities.GoalEntity
import com.example.tcc.databinding.ActivityAddActivityBinding
import com.example.tcc.databinding.ActivityFormGoalBinding
import com.example.tcc.presentation.dialog.ConfirmDeleteDialogFragment
import com.example.tcc.presentation.viewModel.ActivityViewModel
import com.example.tcc.presentation.viewModel.GoalFormViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddActivityActivity : BaseActivity() {



    private val viewModel: ActivityViewModel by viewModels()
    private lateinit var binding: ActivityAddActivityBinding
    private lateinit var date: Date
    private var activity: ActivityEntity? = null
    private var classId: Long = -1
    private var disciplineId: Long = -1
    private var classes: List<ClassEntity> = emptyList()
    private var disciplines: List<DisciplineEntity> = emptyList()
    private var isEvaluative: Boolean? = null
    private var yesOrNoList = listOf(YES to "Sim", NO to "Não")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activity = intent.getSerializableExtra(SELECTED_ACTIVITY) as? ActivityEntity

        binding.btnAdicionar.text = activity?.let { "Atualizar" } ?: "Adicionar"

        binding.etTurma.isEnabled = AppSingleton.isTeacher
        binding.etMateria.isEnabled = AppSingleton.isTeacher
        binding.etAtividadeAvaliativa.isEnabled = AppSingleton.isTeacher
        binding.etConteudo.isEnabled = AppSingleton.isTeacher
        binding.etDescricao.isEnabled = AppSingleton.isTeacher
        binding.btnAdicionar.isVisible = AppSingleton.isTeacher

        CoroutineScope(Dispatchers.IO).launch {
            if (AppSingleton.isTeacher.not()) return@launch
            classes = AppDataBase.getInstance(applicationContext).teacherDAO()
                .getTeacherWithClasses(AppSingleton.userId).classes
        }




        CoroutineScope(Dispatchers.IO).launch {
            if (AppSingleton.isTeacher.not()) return@launch
            disciplines = AppDataBase.getInstance(applicationContext).teacherDAO()
                .getTeacherWithDisciplines(AppSingleton.userId).disciplines
        }

        activity?.let { act ->
            CoroutineScope(Dispatchers.IO).launch {
                val className = AppDataBase.getInstance(this@AddActivityActivity).classDAO()
                    .getClassBy(act.classId).name
                val disciplineName =
                    AppDataBase.getInstance(this@AddActivityActivity).disciplineDAO()
                        .getDisciplineBy(act.disciplineId).name
                withContext(Dispatchers.Main) {
                    binding.etTurma.setText(className)
                    binding.etMateria.setText(disciplineName)
                }
            }
            binding.etConteudo.setText(act.title)
            binding.etDescricao.setText(act.description)
            isEvaluative = act.isEvaluative
            binding.etAtividadeAvaliativa.setText(
                (if (act.isEvaluative) {
                    yesOrNoList.first { it.first == YES }
                } else {
                    yesOrNoList.first { it.first == NO }
                }).second
            )
        }
        // Receber a data do intent

        binding.tvDate.text = runCatching {
            date = intent.getSerializableExtra(SELECTED_DATE) as Date
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
        }.getOrElse { "Data não selecionada" }

        binding.btnAdicionar.setOnClickListener {
            val title = binding.etConteudo.text.toString()
            val description = binding.etDescricao.text.toString()

            if (classId == -1L || disciplineId == -1L || isEvaluative == null || title.isBlank()) {
                Toast.makeText(
                    this@AddActivityActivity,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val newActivity = ActivityEntity(
                id = activity?.id ?: 0,
                title = title,
                description = description,
                date = date,
                classId = classId,
                disciplineId = disciplineId,
                isEvaluative = isEvaluative ?: false
            )

            // Salvar no banco de dados
            CoroutineScope(Dispatchers.IO).launch {
                if (activity != null) {
                    AppDataBase.getInstance(applicationContext).activityDAO().update(newActivity)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddActivityActivity,
                            "Atividade atualizada com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    AppDataBase.getInstance(applicationContext).activityDAO().insert(newActivity)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@AddActivityActivity,
                            "Atividade adicionada com sucesso",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                finish() // Fechar a atividade após adicionar
            }
        }
        binding.btnDelete.isVisible = AppSingleton.isTeacher


        binding.etTurma.setOnClickListener {
            showPopupMenu(
                anchorView = binding.etTurma,
                items = classes.map { it.classId to it.name }) {
                classId = it.first
                binding.etTurma.setText(it.second)
            }
        }
        binding.etMateria.setOnClickListener {
            showPopupMenu(
                anchorView = binding.etMateria,
                items = disciplines.map { it.disciplineId to it.name }
            ) {
                disciplineId = it.first
                binding.etMateria.setText(it.second)
            }
        }
        binding.etAtividadeAvaliativa.setOnClickListener {
            showPopupMenu(
                anchorView = binding.etAtividadeAvaliativa,
                items = yesOrNoList
            ) {
                isEvaluative = it.first == YES
                binding.etAtividadeAvaliativa.setText(it.second)
            }
        }



            binding.btnDelete.setOnClickListener {
            val dialog = ConfirmDeleteDialogFragment { isConfirmed ->
                if (isConfirmed) {
                    activity?.let {
                        viewModel.deleteActivity(it)
                        showToast("Meta excluída com sucesso!")
                        finish()
                    }
                }
            }
            dialog.show(supportFragmentManager, "ConfirmDeleteDialog")
        }



    }

    companion object {
        private const val YES = 0L
        private const val NO = 1L

        const val SELECTED_DATE = "SELECTED_DATE"
        const val SELECTED_ACTIVITY = "SELECTED_ACTIVITY"
    }
}