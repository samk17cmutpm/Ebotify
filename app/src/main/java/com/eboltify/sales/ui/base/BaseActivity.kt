package com.eboltify.sales.ui.base

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import android.widget.Toast
import com.eboltify.sales.R
import com.eboltify.sales.ui.handle_error.HandleErrorActivity
import com.eboltify.sales.ui.lib.CCFAnimator
import com.eboltify.sales.ui.lib.WindowCompat
import com.eboltify.sales.ui.lib.WindowCompat21
import com.eboltify.sales.ui.lib.WindowCompatImpl

/**
 * Created by sam_nguyen on 3/28/18.
 */
open class BaseActivity : AppCompatActivity(), BaseViewActions {

    lateinit var mWindowCompat: WindowCompat

    var mPrimaryColor: Int = R.color.colorPrimary

    var mAccentColor: Int = R.color.colorAccent

    override fun handleInternetException(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun handleStatusCodeError(code: Int) {
        when (code) {
            401 -> {
                HandleErrorActivity.start(this)
                finish()
            }
            500 -> {

            }
        }
    }

    override fun initToolBar(toolbar: Toolbar, title: String, icon: Int) {
        setSupportActionBar(toolbar)
        toolbar.setContentInsetsAbsolute(0, 0)
        if (icon != 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            toolbar.setNavigationIcon(icon)
        }
        if (!title.isEmpty()) {
            val titleToolBarTextView = toolbar.findViewById<TextView>(R.id.toolbar_title)
            titleToolBarTextView.text = title
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWindowCompat = windowCompat(this)
    }

    fun windowCompat(activity: Activity): WindowCompat {
        var compat: WindowCompat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            compat = WindowCompat21(activity.window)
        } else {
            compat = WindowCompatImpl()
        }
        return compat
    }

    fun changeStatusBarColor(color: Int) {
        val primary = CCFAnimator.rgb(mPrimaryColor, color)
        val mPrimaryAnimator = primary.asValueAnimator(CCFAnimator.OnNewColorListener { color ->
            mWindowCompat.setStatusBarColor(color)
        })
        mPrimaryAnimator?.setDuration(250L)
        mPrimaryAnimator?.start()
    }
}