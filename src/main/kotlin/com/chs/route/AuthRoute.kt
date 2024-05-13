package com.chs.route

import com.chs.data.request.RequestAccessToken
import com.chs.data.request.RequestLogin
import com.chs.data.responses.ResponseAccessToken
import com.chs.data.responses.ResponseBase
import com.chs.data.responses.ResponseLogin
import com.chs.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

fun Route.authRoute(userService: UserService) {

    post {
        val loginRequest = call.receive<RequestLogin>()

        val authResponse: ResponseLogin? = userService.authenticate(loginRequest)

        authResponse?.let {
            call.respond(authResponse)
        } ?: call.respond(
            ResponseBase(
                code = HttpStatusCode.BadRequest.value,
                message = "Not Correct UserInfo."
            )
        )
    }

    post("/refresh") {
        val request = call.receive<RequestAccessToken>()

        val newAccessToken = userService.refreshToken(token = request.refreshToken)

        newAccessToken?.let {
            call.respond(
                ResponseAccessToken(it)
            )
        } ?: call.respond(
            ResponseBase(
                code = HttpStatusCode.Unauthorized.value,
                message = "You're UnAuthorized."
            )
        )
    }
}

