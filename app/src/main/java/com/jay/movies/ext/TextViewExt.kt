package com.jay.movies.ext

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter

@BindingAdapter("textRes")
fun TextView.bindTextRes(@StringRes textRes: Int) {
    text = this.context.getString(textRes)
}