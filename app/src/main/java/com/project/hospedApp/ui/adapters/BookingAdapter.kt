package com.project.hospedApp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.project.hospedApp.R
import com.project.hospedApp.data.models.Booking

class BookingAdapter(private val bookingList: List<Booking>) :
    Adapter<BookingAdapter.BookingViewHolder>() {

    class BookingViewHolder(itemView: View) : ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name_item_reservation)
        val checkIn: TextView = itemView.findViewById(R.id.checkin_item_reservation)
        val checkOut: TextView = itemView.findViewById(R.id.checkout_item_reservation)
        val additionalNeeds: TextView =
            itemView.findViewById(R.id.additional_needs_item_reservation)
        val price: TextView = itemView.findViewById(R.id.total_price_item_reservation)
        val depositPaid: ImageView = itemView.findViewById(R.id.depositpaid_item_reservation)
    }

    override fun getItemCount() = bookingList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_reservation, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val fullName = bookingList[position].firstname + " " + bookingList[position].lastname
        holder.name.text = fullName
        holder.checkIn.text = bookingList[position].bookingdates.checkin
        holder.checkOut.text = bookingList[position].bookingdates.checkout
        holder.additionalNeeds.text = bookingList[position].additionalneeds
        holder.price.text = bookingList[position].totalprice.toString()

        if (bookingList[position].depositpaid) {
            holder.depositPaid.setImageResource(R.drawable.ic_depositpaid_true)
        } else {
            holder.depositPaid.setImageResource(R.drawable.ic_depositpaid_false)
        }
    }
}
