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
import com.eboltify.sales.ui.sign_in.SignInActivity
import io.fabric.sdk.android.Fabric


class SplashActivity : BaseActivity() {

    private val SPLASH_TIME_OUT : Long = 500;

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

    private fun startMainActivity() {
        SignInActivity.start(this)
        finish()
    }
}
