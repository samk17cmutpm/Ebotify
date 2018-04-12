package com.eboltify.sales.ui.sign_in

import com.eboltify.sales.data.remote.AuthenticationSourceRemote
import com.eboltify.sales.data.repositories.AuthenticationDataRepository
import com.eboltify.sales.domain.interactors.authentication.SignInInterator
import com.eboltify.sales.domain.repositories.AuthenticationRepository
import com.eboltify.sales.remote.net.ServiceGenerator
import com.eboltify.sales.remote.services.AuthenticationService
import com.eboltify.sales.remote.sources.AuthenticationDataSourceRemote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SignInModule constructor(private val mView: SignInContract.View) {

    @Provides
    fun providesSignInPresenter(mSignInInterator: SignInInterator) : SignInContract.Presenter {
        return SignInPresenter(mView, mSignInInterator)
    }

    @Provides
    fun providesSignInInterator(mAuthenticationRepository: AuthenticationRepository) : SignInInterator {
        return SignInInterator(mAuthenticationRepository)
    }

    @Provides
    fun providesSignInView() : SignInContract.View {
        return mView
    }

    @Provides
    @Singleton
    fun providesAuthenticationService() : AuthenticationService {
        return ServiceGenerator.createService(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthenticationSourceRemote(mAuthenticationService: AuthenticationService) : AuthenticationSourceRemote {
        return AuthenticationDataSourceRemote(mAuthenticationService)
    }

    @Provides
    @Singleton
    fun providesAuthenticationRepository(mAuthenticationSourceRemote: AuthenticationSourceRemote) : AuthenticationRepository {
        return AuthenticationDataRepository(mAuthenticationSourceRemote)
    }

}