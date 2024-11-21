package com.betrybe.trybnb.data.models

data class Booking(
    val firstname: String,
    val lastname: String,
    val totalprice: Int,
    val depositpaid: Boolean,
    val bookingdates: BookingDates,
    val additionalneeds: String?
)
