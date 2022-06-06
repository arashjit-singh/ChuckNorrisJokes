package com.chucknorrisjokes.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.chucknorrisjokes.R
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


object UtilityClass {

    fun setImageViaGlide(
        placeholderId: Int,
        imageUrl: String,
        imgVw: ImageView,
        mContext: Context
    ) {
        if (!TextUtils.isEmpty(imageUrl)) {
            val requestOptions: RequestOptions =
                RequestOptions().placeholder(placeholderId).error(placeholderId)
                    .fallback(placeholderId).diskCacheStrategy(
                        DiskCacheStrategy.ALL
                    )
            Glide.with(mContext).load(imageUrl).apply(requestOptions).into(imgVw)
        }
    }

    fun getDate(milliSeconds: Long, dateFormat: String?, timezone: Boolean): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds * 1000
        if (timezone) {
            calendar.add(
                Calendar.MILLISECOND,
                TimeZone.getDefault().getOffset(calendar.timeInMillis)
            )
        }
        return formatter.format(calendar.time)
    }

    fun hideUnhideImg(showImg: Boolean, imgView: ImageView, imgCode: Int) {
        if (!showImg)
            imgView.visibility = View.GONE
        else {
            imgView.setImageResource(imgCode)
            imgView.visibility = View.VISIBLE
        }
    }

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

    fun setRecyclerViewAnimation(recyclerView: RecyclerView, mContext: Context) {
        val resId = R.anim.layout_animation_fall_down
        val animation = AnimationUtils.loadLayoutAnimation(mContext, resId)
        recyclerView.setLayoutAnimation(animation)
    }
}