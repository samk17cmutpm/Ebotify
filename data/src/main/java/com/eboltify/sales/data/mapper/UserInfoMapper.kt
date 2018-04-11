package com.eboltify.sales.data.mapper

import com.eboltify.sales.data.model.UserInfoData
import com.eboltify.sales.domain.model.UserInfo

class UserInfoMapper : EntityMapper<UserInfoData, UserInfo> {
    override fun mapFromRemote(type: UserInfoData): UserInfo {
        return UserInfo(type.token)
    }
}