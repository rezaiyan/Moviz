package ir.alirezaiyan.moviz.sdk.platform.extension

import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan

fun String.nextLine(): String {
    return "$this \n\n"
}

fun String.toSpan(size: Int): SpannableString {
    val span = SpannableString(this)
    span.setSpan(AbsoluteSizeSpan(size), 0, this.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
    return span
}