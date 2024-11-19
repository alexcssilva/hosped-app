package com.betrybe.trybnb.data.network

import com.betrybe.trybnb.data.api.ApiServiceClient
import com.betrybe.trybnb.data.models.LoginResponse
import com.betrybe.trybnb.data.models.Token

class LoginDataSource {

    private val service = ApiServiceClient.instance

    suspend fun login(email: String, password: String): Token? {
        val loginBody = LoginResponse(email, password)
        val loginResponse = service.login(loginBody)

        return loginResponse.body()
    }
}