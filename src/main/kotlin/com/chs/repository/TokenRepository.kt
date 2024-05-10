package com.chs.repository

class TokenRepository {

    private val tokens = mutableMapOf<String, String>()

    fun findUserIdByToken(token: String): String? = tokens[token]

    fun saveUserToken(
        token: String,
        userId: String
    ) {
        tokens[token] = userId
    }
}