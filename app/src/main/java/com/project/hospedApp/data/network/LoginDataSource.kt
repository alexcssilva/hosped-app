package com.project.hospedApp.data.network

import com.project.hospedApp.data.api.ApiServiceClient
import com.project.hospedApp.data.models.LoginResponse
import com.project.hospedApp.data.models.Token

class LoginDataSource {

    private val service = ApiServiceClient.instance

    suspend fun login(email: String, password: String): Token? {
        val loginBody = LoginResponse(email, password)
        val loginResponse = service.login(loginBody)

        return loginResponse.body()
    }
}
