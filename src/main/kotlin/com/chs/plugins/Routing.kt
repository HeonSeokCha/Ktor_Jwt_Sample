package com.chs.plugins

import com.chs.authenticate
import com.chs.data.user.UserDataSource
import com.chs.getSecretInfo
import com.chs.security.hashing.HashingService
import com.chs.security.token.TokenConfig
import com.chs.security.token.TokenService
import com.chs.signIn
import com.chs.signUp
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {

    routing {
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
        getSecretInfo()
    }
}
