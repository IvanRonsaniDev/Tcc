package com.example.tcc

import android.os.Bundle
import android.widget.Toast
import com.example.tcc.core.base.BaseActivity
import com.example.tcc.core.extensions.showPopupMenu
import com.example.tcc.data.db.AppDataBase
import com.example.tcc.data.db.entities.ActivityEntity
import com.example.tcc.data.db.entities.ClassEntity
import com.example.tcc.data.db.entities.DisciplineEntity
import com.example.tcc.databinding.ActivityAddActivityBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AddActivityActivity : BaseActivity() {

    private lateinit var binding: ActivityAddActivityBinding
    private lateinit var date: Date
    private var activity: ActivityEntity? = null
    private var classId: Long = -1
    private var disciplineId: Long = -1
    private var classes: List<ClassEntity> = emptyList()
    private var disciplines: List<DisciplineEntity> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activity = intent.getSerializableExtra(SELECTED_ACTIVITY) as? ActivityEntity

        CoroutineScope(Dispatchers.IO).launch {
            classes = AppDataBase.getInstance(applicationContext).classDAO().getAll()
            withContext(Dispatchers.Main) {
                activity?.let { act ->
                    val `class` = classes.firstOrNull { act.classId == it.id }
                    classId = `class`?.id ?: -1
                    binding.etTurma.setText(`class`?.name)
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            disciplines = AppDataBase.getInstance(applicationContext).disciplineDAO().getAll()
            withContext(Dispatchers.Main) {
                activity?.let { act ->
                    val discipline = disciplines.firstOrNull { act.disciplineId == it.disciplineId }
                    disciplineId = discipline?.disciplineId ?: -1
                    binding.etMateria.setText(discipline?.name)
                }
            }
        }

        activity?.let { act ->
            binding.etConteudo.setText(act.title)
            binding.etDescricao.setText(act.description)
        }
        // Receber a data do intent

        binding.tvDate.text = runCatching {
            date = intent.getSerializableExtra(SELECTED_DATE) as Date
            SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
        }.getOrElse { "Data não selecionada" }

        binding.btnAdicionar.setOnClickListener {
            val title = binding.etConteudo.text.toString()
            val description = binding.etDescricao.text.toString()

            if (classId == -1L || disciplineId == -1L || title.isBlank()) {
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
                disciplineId = disciplineId
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

        binding.etTurma.setOnClickListener {
            showPopupMenu(anchorView = binding.etTurma, items = classes.map { it.id to it.name }) {
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
    }

    companion object {
        const val SELECTED_DATE = "SELECTED_DATE"
        const val SELECTED_ACTIVITY = "SELECTED_ACTIVITY"
    }
}