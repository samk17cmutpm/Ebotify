package com.eboltify.sales.mapper

import com.eboltify.sales.domain.model.UserInfo
import com.eboltify.sales.view_model.UserInfoViewModel

class UserInfoFromDomain : FromDomainMapper<UserInfo, UserInfoViewModel> {
    override fun mapFromDomain(type: UserInfo): UserInfoViewModel {
        return UserInfoViewModel(type.token)
    }
}