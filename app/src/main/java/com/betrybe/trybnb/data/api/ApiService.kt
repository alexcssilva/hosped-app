package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.BookingId
import com.betrybe.trybnb.data.models.CreatedBooking
import com.betrybe.trybnb.data.models.LoginResponse
import com.betrybe.trybnb.data.models.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("auth")
    suspend fun login(@Body auth: LoginResponse): Response<Token>

    @GET("booking")
    suspend fun getAllBookingIds(): Response<List<BookingId>>

    @GET("booking/{id}")
    @Headers("Accept: application/json")
    suspend fun getBookingById(
        @Path("id") id: String
    ): Response<Booking>

    @POST("booking")
    suspend fun createBooking(
        @Body booking: Booking
    ): Response<CreatedBooking>
}
