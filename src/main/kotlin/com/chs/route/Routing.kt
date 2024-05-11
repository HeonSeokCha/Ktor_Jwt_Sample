package com.chs.route

import com.chs.service.UserService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    userService: UserService
) {
    routing {

        get {
            call.respond("This Simple Test JWT Auth API.")
        }

        route("/api/auth") {
            authRoute(userService)
        }

        route("/api/user") {
            userRoute(userService)
        }
    }
}