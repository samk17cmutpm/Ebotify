package com.eboltify.sales.ui.lib

import android.annotation.TargetApi
import android.os.Build
import android.view.Window

/**
 * Created by sam_nguyen on 3/28/18.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class WindowCompat21 constructor(val mWindow: Window) : WindowCompat {
    override fun setStatusBarColor(color: Int) {
        mWindow.statusBarColor = color
    }
}