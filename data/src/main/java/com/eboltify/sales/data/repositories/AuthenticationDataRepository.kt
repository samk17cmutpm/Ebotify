package com.eboltify.sales.data.repositories

import com.eboltify.sales.data.mapper.UserInfoDataMapperForDomain
import com.eboltify.sales.data.remote.AuthenticationSourceRemote
import com.eboltify.sales.domain.model.UserInfo
import com.eboltify.sales.domain.repositories.AuthenticationRepository
import io.reactivex.Flowable

class AuthenticationDataRepository(private val mAuthenticationDataSourceRemote: AuthenticationSourceRemote) : AuthenticationRepository {

    override fun signIn(uuid: String, email: String, password: String): Flowable<UserInfo> {
        return mAuthenticationDataSourceRemote.signIn(uuid, email, password).map { t ->
            UserInfoDataMapperForDomain().map(t)
        }
    }
}