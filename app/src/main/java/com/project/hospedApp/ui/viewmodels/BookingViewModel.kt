package com.project.hospedApp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.hospedApp.common.ApiIdlingResource
import com.project.hospedApp.data.models.Booking
import com.project.hospedApp.data.models.BookingId
import com.project.hospedApp.data.repository.BookingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookingViewModel : ViewModel() {
    private val mBookingRepository = BookingRepository()

    private var _bookingIds = MutableLiveData<List<BookingId>>()
    val bookingIds: LiveData<List<BookingId>>
        get() = _bookingIds

    private var _bookings = MutableLiveData<List<Booking>>()
    val bookings: LiveData<List<Booking>>
        get() = _bookings

    private var _errorMessage = MutableLiveData("")
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var _isErrorOccurred = MutableStateFlow(false)
    val isErrorOccurred: StateFlow<Boolean>
        get() = _isErrorOccurred

    fun getAllBookingIds() {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val response = mBookingRepository.getAllBookingIds()
            if (response.success) {
                _bookingIds.postValue(
                    response.data!!.shuffled().subList(
                        0,
                        5
                    )
                )
                _isErrorOccurred.value = false
            } else {
                _errorMessage.postValue(response.message)
                _isErrorOccurred.value = true
            }
            ApiIdlingResource.decrement()
        }
    }

    fun getBookingById(bookingIds: List<BookingId>) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val responseList = ArrayList<Booking>()
            for (id in bookingIds) {
                val response = mBookingRepository.getBookingById(id.bookingid.toString())
                if (response.success) {
                    responseList.add(response.data!!)
                    _isErrorOccurred.value = false
                } else {
                    _errorMessage.postValue(response.message)
                    _isErrorOccurred.value = true
                }
            }
            _bookings.postValue(responseList)
            ApiIdlingResource.decrement()
        }
    }
}
