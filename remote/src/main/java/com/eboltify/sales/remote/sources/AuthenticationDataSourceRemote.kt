package com.eboltify.sales.remote.sources

import com.eboltify.sales.data.model.UserInfoData
import com.eboltify.sales.data.remote.AuthenticationSourceRemote
import com.eboltify.sales.remote.errors.ErrorMessageFactory
import com.eboltify.sales.remote.mapper.UserInfoMapperForData
import com.eboltify.sales.remote.request.UserInfoRequest
import com.eboltify.sales.remote.response.UserInfoResponse
import com.eboltify.sales.remote.services.AuthenticationService
import io.reactivex.Flowable
import io.reactivex.functions.Function
import retrofit2.Response
import javax.inject.Inject

class AuthenticationDataSourceRemote @Inject constructor(private val mAuthenticationService: AuthenticationService) : AuthenticationSourceRemote {
    override fun signIn(uuid: String, email: String, password: String): Flowable<UserInfoData> {
        return mAuthenticationService.fetchUserInfo(UserInfoRequest(uuid, email, password))
                .map(object : Function<Response<UserInfoResponse>, UserInfoData> {
                    override fun apply(t: Response<UserInfoResponse>): UserInfoData {
                        if (t.isSuccessful) {
                            return UserInfoMapperForData().map(t.body()!!)
                        } else {
                            throw Throwable(ErrorMessageFactory.getErrorMessage(t.errorBody()!!))
                        }
                    }
                })
    }
}