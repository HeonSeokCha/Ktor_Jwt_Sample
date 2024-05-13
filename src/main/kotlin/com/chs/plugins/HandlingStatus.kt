package com.chs.plugins

import com.chs.data.responses.ResponseBase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureHandlingStatus() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if (cause is BadRequestException) {
                call.respond(
                    ResponseBase(
                        code = HttpStatusCode.BadRequest.value,
                        message = "Bad Request."
                    )
                )
            } else {
                call.respond(
                    ResponseBase(
                        code = HttpStatusCode.InternalServerError.value,
                        message = cause.message ?: "Unknown ERROR."
                    )
                )
            }
        }

        status(HttpStatusCode.NotFound) { call, cause ->
            call.respond(
                ResponseBase(
                    code = HttpStatusCode.NotFound.value,
                    message = "Not Found."
                )
            )
        }

        status(HttpStatusCode.BadRequest) { call, cause ->
            call.respond(
                ResponseBase(
                    code = HttpStatusCode.BadRequest.value,
                    message = "Bad Request."
                )
            )
        }

        status(HttpStatusCode.MethodNotAllowed) { call, cause ->
            call.respond(
                ResponseBase(
                    code = HttpStatusCode.MethodNotAllowed.value,
                    message = "Not Allowed Method"
                )
            )
        }

        status(HttpStatusCode.Unauthorized) { call, cause ->
            call.respond(
                ResponseBase(
                    code = HttpStatusCode.Unauthorized.value,
                    message = "You're UnAuthorized."
                )
            )
        }
    }
}