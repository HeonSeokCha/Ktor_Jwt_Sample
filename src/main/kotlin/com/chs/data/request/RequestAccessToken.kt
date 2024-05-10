package com.chs.data.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestAccessToken(
    val refreshToken: String
)
