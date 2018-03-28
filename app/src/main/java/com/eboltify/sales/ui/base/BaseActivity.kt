package com.eboltify.sales.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TextView
import android.widget.Toast
import com.eboltify.sales.R
import com.eboltify.sales.ui.handle_error.HandleErrorActivity

/**
 * Created by sam_nguyen on 3/28/18.
 */
open class BaseActivity : AppCompatActivity(), BaseViewActions {

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
    }
}