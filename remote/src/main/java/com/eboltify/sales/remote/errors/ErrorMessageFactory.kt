package com.eboltify.sales.remote.errors

import com.google.gson.JsonObject
import okhttp3.ResponseBody
import com.google.gson.JsonParser



class ErrorMessageFactory {

     companion object {
         val NETWORK_INAVAILABLE = "Internet is inavailable"
         val SOMETHING_WENT_WRONG = "Something went wrong"

         fun getErrorMessage(responseBody: ResponseBody): String {
             val jsonObject = JsonParser().parse(responseBody.string()).asJsonObject
             return jsonObject.get("message").asString

         }
     }

}