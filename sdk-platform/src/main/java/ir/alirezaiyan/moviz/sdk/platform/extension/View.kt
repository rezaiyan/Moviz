package ir.alirezaiyan.moviz.sdk.platform.extension

import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.TextView

fun TextView.clear() {
    text = ""
}

private const val DRAWABLE_RIGHT = 2
fun EditText.setOnRightDrawableClickListener(onclick: () -> Unit) {
    setOnTouchListener(View.OnTouchListener { _, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= right - compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                onclick.invoke()
                return@OnTouchListener true
            }
        }
        false
    })
}