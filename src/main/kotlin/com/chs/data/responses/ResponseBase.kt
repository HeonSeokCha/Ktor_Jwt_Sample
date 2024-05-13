package com.chs.data.responses

import kotlinx.serialization.Serializable

@Serializable
data class ResponseBase(
    val code: Int,
    val message: String?
)
