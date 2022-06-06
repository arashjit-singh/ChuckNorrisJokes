package com.chucknorrisjokes.base

import android.app.Application
import com.chucknorrisjokes.R
import com.rommansabbir.networkx.NetworkXConfig
import com.rommansabbir.networkx.NetworkXProvider
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/Lato-Regular.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )

        val builder = NetworkXConfig.Builder()
            .withApplication(this)
            .build()

        NetworkXProvider.enable(builder)
    }

}