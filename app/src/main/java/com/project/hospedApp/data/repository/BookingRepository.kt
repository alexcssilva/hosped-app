package com.project.hospedApp.data.repository

import com.project.hospedApp.data.models.Booking
import com.project.hospedApp.data.models.BookingId
import com.project.hospedApp.data.models.CreatedBooking
import com.project.hospedApp.data.models.Response
import com.project.hospedApp.data.network.BookingDataSource
import java.net.ConnectException

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
            val bookingResponse = mBookingDataSource.createBooking(booking)
            return Response(true, "", bookingResponse)
        } catch (e: ConnectException) {
            return Response(false, e.message.toString(), null)
        }
    }
}
