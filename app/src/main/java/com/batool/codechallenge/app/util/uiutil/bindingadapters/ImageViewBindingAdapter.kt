package com.batool.codechallenge.app.util.uiutil.bindingadapters

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.batool.codechallenge.R
import com.batool.codechallenge.app.util.uiutil.getCircularProgressDrawable


/**
 * Created By Batool Mofeed on 8/31/2023.
 **/
object ImageViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("loadRoundedImage")
    fun AppCompatImageView.loadRoundedImage(imageUrl: String?) {
        imageUrl?.let { url ->
            if (url.isNotEmpty()) {
                load(imageUrl) {
                    transformations(RoundedCornersTransformation(20F, 20F, 20F, 20F))
                    placeholder(getCircularProgressDrawable(context))
                    error(R.drawable.logo)
                }
            } else {
                load(R.drawable.logo)
            }
        }
    }
}