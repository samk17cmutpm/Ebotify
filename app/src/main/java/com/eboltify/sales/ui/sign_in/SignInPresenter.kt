package com.eboltify.sales.ui.sign_in

import com.eboltify.sales.domain.interactors.authentication.SignInInterator
import com.eboltify.sales.domain.model.UserInfo
import io.reactivex.subscribers.DisposableSubscriber

class SignInPresenter constructor(val mView: SignInContract.View, private val mSignInInterator: SignInInterator): SignInContract.Presenter {

    override fun signIn(uuid: String, email: String, password: String) {
        mSignInInterator.run(
                SignInSubscriber(),
                SignInInterator.RequestValues(uuid, email, password)
        )
    }

    override fun detachView() {
    }

    inner class SignInSubscriber : DisposableSubscriber<UserInfo>() {
        override fun onComplete() {
        }

        override fun onNext(t: UserInfo) {
            mView.signInSuccess()
        }

        override fun onError(e: Throwable) {
            mView.signInFailed(e.message!!)
        }

    }
}