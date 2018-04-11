package com.eboltify.sales.ui.sign_in

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.eboltify.sales.R
import com.eboltify.sales.data.repositories.AuthenticationDataRepository
import com.eboltify.sales.domain.interactors.authentication.SignInInterator
import com.eboltify.sales.domain.repositories.AuthenticationRepository
import com.eboltify.sales.remote.net.ServiceGenerator
import com.eboltify.sales.remote.services.AuthenticationService
import com.eboltify.sales.remote.sources.AuthenticationDataSourceRemote
import com.eboltify.sales.ui.base.BaseActivity
import com.eboltify.sales.ui.main.MainActivity
import com.eboltify.sales.utils.ValidationUtils

class SignInActivity : BaseActivity(), SignInContract.View {


    lateinit var mPresenter: SignInContract.Presenter

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    @BindView(R.id.toolbar)
    lateinit var mToolbar: Toolbar

    @BindView(R.id.email)
    lateinit var mEmail: EditText

    @BindView(R.id.uuid)
    lateinit var mUuid: EditText

    @BindView(R.id.password)
    lateinit var mPassword: EditText

    var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initUI()
        mPresenter = SignInPresenter(this, SignInInterator(AuthenticationDataRepository(AuthenticationDataSourceRemote(ServiceGenerator.createService(AuthenticationService::class.java)))))
    }

    override fun validateInputs(): Boolean {
        mEmail.error = null
        mPassword.error = null
        mUuid.error = null

        val email = mEmail.editableText.toString()
        val uuid = mUuid.editableText.toString()
        val password = mPassword.editableText.toString()

        var cancel = false
        var focusView: View? = null

        if (!ValidationUtils.isValidPassword(password)) {
            mPassword.error = getString(R.string.field_input_password_minimum)
            focusView = mPassword
            cancel = true
        }

        if (!TextUtils.isEmpty(email) && !ValidationUtils.isValidEmail(email)) {
            mEmail.error = getString(R.string.field_input_email_invalid)
            focusView = mEmail
            cancel = true
        }

        if (TextUtils.isEmpty(uuid)) {
            mUuid.error = getString(R.string.field_input_cannot_be_empty)
            focusView = mUuid
            cancel = true
        }

        if (cancel)
            focusView?.requestFocus()

        return !cancel

    }

    override fun signInSuccess() {
        showDialogLoading(false, "")
        MainActivity.start(this)
        finish()
    }

    override fun signInFailed(message: String) {
        showDialogLoading(false, "")
        runOnUiThread { Toast.makeText(this, message, Toast.LENGTH_LONG).show() }
    }

    override fun initUI() {
        changeStatusBarColor(ContextCompat.getColor(applicationContext, R.color.md_amber_500))
        ButterKnife.bind(this)
        setSupportActionBar(mToolbar)
        initToolBar(mToolbar, getString(R.string.sign_in), 0)
        mToolbar.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.md_amber_500))
        mProgressDialog = ProgressDialog(this)
        mProgressDialog?.setCancelable(false)
        mProgressDialog?.isIndeterminate = true
    }

    override fun initData() {

    }

    @OnClick(R.id.sign_in)
    fun onClickSignIn() {
        if (validateInputs()) {
            showDialogLoading(true, getString(R.string.sign_in_loading))
            mPresenter.signIn(mUuid.editableText.toString(), mEmail.editableText.toString(), mPassword.editableText.toString())
        }
    }

    override fun showDialogLoading(activated: Boolean, message: String) {
        if (activated) {
            mProgressDialog?.setMessage(message)
            if (!mProgressDialog?.isShowing!!) {
                mProgressDialog?.show()
            }
        } else {
            if (mProgressDialog?.isShowing!!) {
                mProgressDialog?.dismiss()
            }
        }
    }
}
