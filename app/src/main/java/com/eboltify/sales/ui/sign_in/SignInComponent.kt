package com.eboltify.sales.ui.sign_in

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [SignInModule::class])
interface SignInComponent {
    fun inject(signInActivity: SignInActivity)
}