package com.eboltify.sales.remote.services

import com.eboltify.sales.remote.request.UserInfoRequest
import com.eboltify.sales.remote.response.UserInfoResponse
import io.reactivex.Flowable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {

    @POST(value = "login")
    fun fetchUserInfo(@Body userInfoRequest: UserInfoRequest) : Flowable<Response<UserInfoResponse>>

}