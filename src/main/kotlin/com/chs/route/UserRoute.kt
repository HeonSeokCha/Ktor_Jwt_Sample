package com.chs.route

import com.chs.data.request.RequestLogin
import com.chs.data.responses.ResponseBase
import com.chs.data.responses.ResponseUserInfo
import com.chs.data.user.UserInfo
import com.chs.service.UserService
import io.ktor.client.plugins.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.random.Random

fun Route.userRoute(userService: UserService) {

    post {
        val request = call.receive<RequestLogin>()

        userService.save(
            UserInfo(
                userId = request.userId,
                userName = "TEST${Random.nextInt(0, 100)}",
                userPassword = request.password
            )
        )
    }

    authenticate {
        get {
            val users = userService.getAllUsers()

            call.respond(
                message = users.map {
                    ResponseUserInfo(
                        userNames = it.userName,
                        userId = it.userId
                    )
                }
            )
        }
    }
}

