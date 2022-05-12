package com.chs.data.user

interface UserDataSource {
    suspend fun getUserByUserName(username: String): User?
    suspend fun insertUser(user: User): Boolean
}