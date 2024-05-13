package com.chs

import com.chs.data.responses.ResponseBase
import io.ktor.server.application.*
import com.chs.plugins.*
import com.chs.repository.TokenRepository
import com.chs.repository.UserRepository
import com.chs.route.configureRouting
import com.chs.service.JwtService
import com.chs.service.UserService
import io.ktor.http.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*


fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    val userRepository = UserRepository()
    val refreshTokenRepository = TokenRepository()
    val jwtService = JwtService(this, userRepository)
    val userService = UserService(userRepository, jwtService, refreshTokenRepository)

    configureHandlingStatus()
    configureSerialization()
    configureSecurity(jwtService)
    configureRouting(userService)
}
