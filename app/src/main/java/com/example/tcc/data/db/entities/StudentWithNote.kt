package com.example.tcc.data.db.entities

data class StudentWithNote(
    val student: StudentEntity,
    val note: NoteEntity? = null
)