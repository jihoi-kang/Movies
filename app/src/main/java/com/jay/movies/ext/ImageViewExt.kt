package com.jay.movies.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(
    "loadUrl",
    "placeholder",
    requireAll = false
)
fun ImageView.bindLoadUrl(
    url: String,
    @DrawableRes placeholder: Int? = null,
) {
    val requestBuilder = Glide.with(this)
        .load(url)

    placeholder?.let {
        requestBuilder.apply(RequestOptions().placeholder(it))
    }
    requestBuilder.into(this)
}