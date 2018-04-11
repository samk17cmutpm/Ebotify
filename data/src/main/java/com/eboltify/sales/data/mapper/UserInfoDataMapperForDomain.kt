package com.eboltify.sales.data.mapper

import com.eboltify.sales.data.model.UserInfoData
import com.eboltify.sales.domain.model.UserInfo

class UserInfoDataMapperForDomain : MapperForDomain<UserInfoData, UserInfo> {
    override fun map(type: UserInfoData): UserInfo {
        return UserInfo(type.token)
    }
}