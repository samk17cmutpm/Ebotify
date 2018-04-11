package com.eboltify.sales.remote.mapper

import com.eboltify.sales.data.model.UserInfoData
import com.eboltify.sales.remote.response.UserInfoResponse

open class UserInfoMapper : EntityMapper<UserInfoResponse, UserInfoData> {
    override fun mapFromRemote(type: UserInfoResponse): UserInfoData {
        return UserInfoData(type.data?.token!!)
    }
}