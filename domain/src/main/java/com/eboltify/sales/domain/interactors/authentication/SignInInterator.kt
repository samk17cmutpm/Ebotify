package com.eboltify.sales.domain.interactors.authentication

import com.eboltify.sales.domain.FlowableUseCase
import com.eboltify.sales.domain.model.UserInfo
import com.eboltify.sales.domain.repositories.AuthenticationRepository
import io.reactivex.Flowable

class SignInInterator constructor(private val mAuthenticationRepository: AuthenticationRepository) : FlowableUseCase<SignInInterator.RequestValues, UserInfo>() {
    override fun buildUseCaseObservable(requestValues: RequestValues): Flowable<UserInfo> {
        return mAuthenticationRepository.signIn(
               uuid =  requestValues.uuid,
               email =  requestValues.email,
               password =  requestValues.password)
    }

    class RequestValues(val uuid: String, val email: String, val password: String) : FlowableUseCase.RequestValues
}