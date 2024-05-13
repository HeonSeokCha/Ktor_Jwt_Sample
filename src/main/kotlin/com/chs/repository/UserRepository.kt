package com.chs.repository

import com.chs.data.user.UserInfo

class UserRepository {
    private val users: MutableList<UserInfo> = mutableListOf(
        UserInfo(
            userName = "TEST1",
            userId = "test1",
            userPassword = "1234"
        ),
        UserInfo(
            userName = "TEST2",
            userId = "test2",
            userPassword = "1234"
        ),
        UserInfo(
            userName = "TEST3",
            userId = "test3",
            userPassword = "1234"
        )
    )

    fun getAll() : List<UserInfo> = users

    fun findByUserId(userId: String): UserInfo? {
        return users.firstOrNull { it.userId == userId }
    }

    fun createUserInfo(userInfo: UserInfo): Boolean = users.add(userInfo)
}