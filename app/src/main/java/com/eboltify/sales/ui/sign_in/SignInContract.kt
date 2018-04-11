package com.eboltify.sales.ui.sign_in

import com.eboltify.sales.ui.base.BasePresenter
import com.eboltify.sales.ui.base.BaseView

interface SignInContract {

    interface View : BaseView {

        fun validateInputs() : Boolean

        fun signInSuccess()

        fun signInFailed(message: String)

        fun showDialogLoading(activated: Boolean, message: String)

    }

    interface Presenter : BasePresenter<View> {

        fun signIn(uuid: String, email: String, password: String)

    }

}