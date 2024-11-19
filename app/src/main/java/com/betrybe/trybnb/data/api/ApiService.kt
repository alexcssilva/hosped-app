package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.LoginResponse
import com.betrybe.trybnb.data.models.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("auth")
    suspend fun login(@Body auth: LoginResponse): Response<Token>
}
