package com.chs.service

import com.auth0.jwt.interfaces.DecodedJWT
import com.chs.data.request.RequestLogin
import com.chs.data.responses.ResponseLogin
import com.chs.data.user.UserInfo
import com.chs.repository.TokenRepository
import com.chs.repository.UserRepository

class UserService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val refreshTokenRepository: TokenRepository
) {

    fun getAllUsers(): List<UserInfo> = userRepository.getAll()

    fun findByUserId(userId: String): UserInfo? =
        userRepository.findByUserId(userId)

    fun save(user: UserInfo): UserInfo? {
        val foundUser = userRepository.findByUserId(user.userId)

        return if (foundUser == null) {
            userRepository.createUserInfo(user)
            user
        } else null
    }

    fun authenticate(loginRequest: RequestLogin): ResponseLogin? {
        val userId = loginRequest.userId
        val foundUser: UserInfo? = userRepository.findByUserId(userId)

        return if (foundUser != null && loginRequest.password == foundUser.userPassword) {
            val accessToken = jwtService.createAccessToken(userId)
            val refreshToken = jwtService.createRefreshToken(userId)

            refreshTokenRepository.saveUserToken(refreshToken, userId)

            ResponseLogin(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        } else null
    }

    fun refreshToken(token: String): String? {
        val decodedRefreshToken = verifyRefreshToken(token)
        val persistedUserId = refreshTokenRepository.findUserIdByToken(token)

        return if (decodedRefreshToken != null && persistedUserId != null) {
            val foundUser: UserInfo? = userRepository.findByUserId(persistedUserId)
            val usernameFromRefreshToken: String? = decodedRefreshToken.getClaim("userId").asString()

            if (foundUser != null && usernameFromRefreshToken == foundUser.userId) {
                jwtService.createAccessToken(persistedUserId)
            } else null
        } else  null
    }

    private fun verifyRefreshToken(token: String): DecodedJWT? {

        return getDecodedJwt(token).run {
            if (this == null) return@run null

            val audienceMatches = jwtService.audienceMatches(this.audience.first())

            return@run if (audienceMatches) this else null
        }
    }

    private fun getDecodedJwt(token: String): DecodedJWT? =
        try {
            jwtService.jwtVerifier.verify(token)
        } catch (ex: Exception) {
            null
        }
}