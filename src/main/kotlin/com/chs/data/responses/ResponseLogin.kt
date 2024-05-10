package com.chs.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class ResponseLogin(
    val accessToken: String,
    val refreshToken: String
)
