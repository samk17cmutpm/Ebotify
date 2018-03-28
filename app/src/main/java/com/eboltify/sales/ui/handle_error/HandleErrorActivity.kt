package com.eboltify.sales.ui.handle_error

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.eboltify.sales.R

class HandleErrorActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HandleErrorActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handle_error)
    }
}
