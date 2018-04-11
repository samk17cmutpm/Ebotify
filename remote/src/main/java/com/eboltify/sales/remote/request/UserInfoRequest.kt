package com.eboltify.sales.remote.request

import com.google.gson.annotations.SerializedName

class UserInfoRequest (
        @field:SerializedName(value = "uuid")
        val uuid: String? = null,
        @field:SerializedName(value = "email")
        val email: String? = null,
        @field:SerializedName(value = "password")
        val password: String? = null
)