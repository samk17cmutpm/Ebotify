package com.eboltify.sales.remote.response

import com.google.gson.annotations.SerializedName


class UserInfoResponse(@field:SerializedName(value = "message")
                       val message: String? = null,
                       @field:SerializedName(value = "data")
                       val data: Data? = null) {
    inner class Data (@field:SerializedName(value = "token")
                      val token: String? = null)
}