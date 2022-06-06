package com.chucknorrisjokes.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chucknorrisjokes.R
import com.google.android.material.snackbar.Snackbar


object UtilityClass {

    fun hideKeyboard(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun showSnackBar(parentLayout: View?, msg: String) {
        if (parentLayout != null) {
            val snackBar = Snackbar.make(parentLayout, msg, Snackbar.LENGTH_SHORT)
            snackBar.setActionTextColor(Color.WHITE)
            val view = snackBar.view
            val tv = view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            tv.setTextColor(Color.WHITE)
            snackBar.show()
        }
    }

    fun showToast(mContext: Context, textToShow: String) {
        Toast.makeText(mContext, textToShow, Toast.LENGTH_SHORT).show()
    }

    fun setRecyclerViewAnimation(recyclerView: RecyclerView, mContext: Context) {
        val resId = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(mContext, resId)
        recyclerView.layoutAnimation = animation
    }
}