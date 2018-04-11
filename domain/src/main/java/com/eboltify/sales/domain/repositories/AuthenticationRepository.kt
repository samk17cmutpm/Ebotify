package com.eboltify.sales.domain.repositories

import com.eboltify.sales.domain.model.UserInfo
import io.reactivex.Flowable

interface AuthenticationRepository {

    fun signIn(uuid: String, email: String, password: String) : Flowable<UserInfo>

}