package com.chs.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserInfo(
    val userNames: String,
    val userId: String
)
