package com.chs.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class ResponseAccessToken(
    val accessToken : String
)
