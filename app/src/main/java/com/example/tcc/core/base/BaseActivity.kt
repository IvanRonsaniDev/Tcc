package com.example.tcc.core.base

import android.graphics.Rect
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.tcc.core.extensions.hideKeyboard


abstract class BaseActivity : AppCompatActivity() {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            currentFocus?.takeIf { it is EditText }?.run {
                val outRect = Rect()
                getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    clearFocus()
                    hideKeyboard()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

}
