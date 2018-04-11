package com.eboltify.sales.data.remote

import com.eboltify.sales.data.model.UserInfoData
import com.eboltify.sales.domain.model.UserInfo
import io.reactivex.Flowable

interface AuthenticationSourceRemote {
    fun signIn(uuid: String, email: String, password: String): Flowable<UserInfoData>
}