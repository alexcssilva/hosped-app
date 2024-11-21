package com.betrybe.trybnb.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private var _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var _isErrorOccurred = MutableStateFlow(false)
    val isErrorOccurred: StateFlow<Boolean>
        get() = _isErrorOccurred

    fun createBooking(booking: Booking) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val response = mBookingRepository.createBooking(booking)
            if (response.success) {
                _isErrorOccurred.value = false
            } else {
                _errorMessage.postValue(response.message)
                _isErrorOccurred.value = true
            }
            ApiIdlingResource.decrement()
        }
    }
}