package com.eboltify.sales.remote.mapper

import com.eboltify.sales.data.model.UserInfoData
import com.eboltify.sales.remote.response.UserInfoResponse

open class UserInfoMapperForData : MapperForData<UserInfoResponse, UserInfoData> {
    override fun map(type: UserInfoResponse): UserInfoData {
        return UserInfoData(type.data?.token!!)
    }
}