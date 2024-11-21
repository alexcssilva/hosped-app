package com.betrybe.trybnb.data.repository

import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.models.BookingId
import com.betrybe.trybnb.data.models.CreatedBooking
import com.betrybe.trybnb.data.models.Response
import com.betrybe.trybnb.data.network.BookingDataSource

class BookingRepository(private val mBookingDataSource: BookingDataSource = BookingDataSource()) {
    suspend fun getAllBookingIds(): Response<List<BookingId>> {
        try {
            val bookingIds = mBookingDataSource.getAllBookingIds()
            if (bookingIds != null) {
                return Response(true, "", bookingIds)
            }
        } catch (e: Exception) {
            return Response(false, e.message.orEmpty(), null)
        }
        return Response(false, "Erro ao carregar os ids de reservas", null)
    }

    suspend fun getBookingById(id: String): Response<Booking> {
        try {
            val booking = mBookingDataSource.getBookingById(id)
            if (booking != null) {
                return Response(true, "", booking)
            }
        } catch (e: Exception) {
            return Response(false, e.message.orEmpty(), null)
        }
        return Response(false, "Erro ao carregar reserva", null)
    }

    suspend fun createBooking(booking: Booking): Response<CreatedBooking> {
        try {
            val createdBooking = mBookingDataSource.createBooking(booking)
            if (createdBooking != null) {
                return Response(true, "", createdBooking)
            }
        } catch (e: Exception) {
            return Response(false, e.message.orEmpty(), null)
        }
        return Response(false, "Erro ao criar reserva", null)
    }
}
