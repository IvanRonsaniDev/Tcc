package com.example.tcc.core.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    activity?.showToast(message, length)
}