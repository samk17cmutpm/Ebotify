package com.eboltify.sales.utils

import android.text.TextUtils

class ValidationUtils private constructor() {

    companion object {
        private val MIN_CHARACTERS_PASSWORD = 6
        private val MIN_CHARACTERS_PHONE_NUMBER = 10
        fun isValidEmail(target: CharSequence): Boolean {
            return if (TextUtils.isEmpty(target)) {
                false
            } else {
                android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
            }
        }

        fun isValidPassword(password: String?): Boolean {
            return if (password != null && password.length >= MIN_CHARACTERS_PASSWORD) true else false
        }

        fun isValidPhoneNumber(phoneNumber: String?): Boolean {
            return if (phoneNumber != null && phoneNumber.length >= MIN_CHARACTERS_PHONE_NUMBER) true else false
        }
    }

}