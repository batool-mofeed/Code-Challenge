package com.batool.codechallenge.app.util.uiutil

import android.content.Context
import androidx.recyclerview.widget.LinearSmoothScroller

/**
 * Created By Batool Mofeed on 9/1/2023.
 **/
class CenterSmoothScroller(context: Context?) : LinearSmoothScroller(context) {
    override fun calculateDtToFit(
        viewStart: Int,
        viewEnd: Int,
        boxStart: Int,
        boxEnd: Int,
        snapPreference: Int
    ): Int {
        return boxStart + (boxEnd - boxStart) / 2 - (viewStart + (viewEnd - viewStart) / 2)
    }
}