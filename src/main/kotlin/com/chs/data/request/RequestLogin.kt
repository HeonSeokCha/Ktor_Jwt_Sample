package com.chs.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestLogin(
    val userId: String,
    val password: String,
)
