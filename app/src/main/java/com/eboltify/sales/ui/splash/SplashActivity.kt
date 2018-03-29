package com.eboltify.sales.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.crashlytics.android.Crashlytics
import com.eboltify.sales.R
import com.eboltify.sales.ui.base.BaseActivity
import com.eboltify.sales.ui.main.MainActivity
import io.fabric.sdk.android.Fabric


class SplashActivity : BaseActivity() {

    companion object {
        val SPLASH_TIME_OUT : Long = 300;
    }

    @BindView(R.id.imgLogo)
    lateinit var mImgLogo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fabric.with(this, Crashlytics())
        setContentView(R.layout.activity_splash)

        ButterKnife.bind(this)

        val animation = AnimationUtils.loadAnimation(this, R.anim.splash)
        mImgLogo.animation = animation

        Handler().postDelayed(Runnable { startMainActivity() }, SPLASH_TIME_OUT)
    }

    fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }
}
