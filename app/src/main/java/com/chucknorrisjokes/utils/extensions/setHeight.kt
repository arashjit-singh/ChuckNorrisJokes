package com.chucknorrisjokes.utils.extensions

import android.view.View

fun View.updateHeight(newHeight: Int) {
    layoutParams = layoutParams.apply {
        height = newHeight
    }
}