package com.betrybe.trybnb.data.network

import com.betrybe.trybnb.data.api.ApiServiceClient
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.BookingId
import com.betrybe.trybnb.data.models.CreatedBooking

class BookingDataSource {
    private val mApiServiceClient = ApiServiceClient.instance

    suspend fun getAllBookingIds(): List<BookingId>? {
        val bookingIds = mApiServiceClient.getAllBookingIds()
        return bookingIds.body()
    }

    suspend fun getBookingById(bookingId: String): Booking? {
        val booking = mApiServiceClient.getBookingById(bookingId)
        return booking.body()
    }

    suspend fun createBooking(booking: Booking): CreatedBooking? {
        val newBooking = mApiServiceClient.createBooking(booking)
        return newBooking.body()
    }
}
