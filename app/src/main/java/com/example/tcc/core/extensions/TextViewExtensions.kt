package com.example.tcc.core.extensions

import android.text.InputFilter
import android.widget.TextView

fun TextView.setMaxLength(maxLength: Int) {
    val filteredFilters = filters.filter { it.javaClass != InputFilter.LengthFilter::class.java }
    filters = (filteredFilters + InputFilter.LengthFilter(maxLength)).toTypedArray()
}