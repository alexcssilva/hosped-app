package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.betrybe.trybnb.common.ApiIdlingResource
import com.betrybe.trybnb.data.models.Booking
import com.betrybe.trybnb.data.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateReservationViewModel : ViewModel() {
    private val mBookingRepository = BookingRepository()

    private val _errorMessage = MutableStateFlow(false)
    val errorMessage: StateFlow<Boolean>
        get() = _errorMessage

    private val _isSuccess = MutableStateFlow(false)
    val isBookingCreationSuccess: StateFlow<Boolean>
        get() = _isSuccess

    private var _isErrorOccurred = MutableStateFlow(false)
    val isErrorOccurred: StateFlow<Boolean>
        get() = _isErrorOccurred

    fun createBooking(body: Booking) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val result = mBookingRepository.createBooking(body)

            if (result.success) _isSuccess.value = true
            ApiIdlingResource.decrement()
        }
    }
}
