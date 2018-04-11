package com.eboltify.sales.mapper

import com.eboltify.sales.domain.model.UserInfo
import com.eboltify.sales.view_model.UserInfoViewModel

class UserInfoForApp : MapperForApp<UserInfo, UserInfoViewModel> {
    override fun map(type: UserInfo): UserInfoViewModel {
        return UserInfoViewModel(type.token)
    }
}