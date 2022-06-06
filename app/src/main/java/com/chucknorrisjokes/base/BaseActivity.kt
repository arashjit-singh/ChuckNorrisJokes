package com.chucknorrisjokes.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.chucknorrisjokes.R
import com.rommansabbir.networkx.NetworkXProvider
import com.rommansabbir.networkx.dialog.NoInternetDialog
import io.github.inflationx.viewpump.ViewPumpContextWrapper


abstract class BaseActivity : AppCompatActivity() {

    fun setToolbarWithBackIcon(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        supportActionBar!!.title = title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    fun checkConnectivityAndShowDialogue(): Boolean {
        if (!NetworkXProvider.isInternetConnected) {
            NoInternetDialog
                .Companion
                .Builder()
                // Provide activity reference
                .withActivity(this)
                // Provide custom title
                .withTitle(getString(R.string.no_internet))
                // Provide custom mesage
                .withMessage(getString(R.string.internet_not_available))
                // Register for callback
                .withActionCallback {
                    // User clicked `Retry` button
                }
                .build()
                .show()
        }
        return NetworkXProvider.isInternetConnected
    }
}