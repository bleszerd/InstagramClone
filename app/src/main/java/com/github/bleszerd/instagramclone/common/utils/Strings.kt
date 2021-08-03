package com.github.bleszerd.instagramclone.common.utils

import java.util.regex.Pattern

/**
InstagramClone
03/08/2021 - 10:02
Created by bleszerd.
@author alive2k@programmer.net
 */
class Strings {
    companion object {
        private val VALID_EMAIL_ADDRESS_REGEX: Pattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

        fun emailIsValid(email: String): Boolean{
            val matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email)
            return matcher.find()
        }
    }
}