package com.eboltify.sales

import android.app.Application
import com.eboltify.sales.ui.lib.CustomViewWithTypefaceSupport
import com.eboltify.sales.ui.lib.TextField
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Created by sam_nguyen on 3/28/18.
 */
class EboltifyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initFontDefautl()
    }

    private fun initFontDefautl() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.font_roboto_regular))
                .setFontAttrId(R.attr.fontPath)
                .addCustomViewWithSetTypeface(CustomViewWithTypefaceSupport::class.java)
                .addCustomStyle(TextField::class.java, R.attr.textFieldStyle)
                .build()
        )
    }
}