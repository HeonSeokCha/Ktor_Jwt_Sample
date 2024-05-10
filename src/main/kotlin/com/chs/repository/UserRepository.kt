package com.chs.repository

import com.chs.data.user.UserInfo

class UserRepository {
    private val users: MutableList<UserInfo> = mutableListOf()

    fun getAll() : List<UserInfo> = users

    fun findByUserId(userId: String): UserInfo? {
        return users.firstOrNull() { it.userId == userId }
    }

    fun createUserInfo(userInfo: UserInfo): Boolean = users.add(userInfo)
}