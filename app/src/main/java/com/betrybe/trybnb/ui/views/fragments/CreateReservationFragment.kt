package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.betrybe.trybnb.databinding.FragmentCreateReservationBinding
import com.google.android.material.textfield.TextInputLayout

class CreateReservationFragment : Fragment() {

    private lateinit var binding: FragmentCreateReservationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_create_reservation, container, false)

        binding = FragmentCreateReservationBinding.bind(view)

        binding.createReservationButton.setOnClickListener {

            val inputs = listOf(
                binding.firstNameCreateReservation to R.string.name_required,
                binding.lastNameCreateReservation to R.string.last_name_required,
                binding.checkinCreateReservation to R.string.checkin_required,
                binding.checkoutCreateReservation to R.string.checkout_required,
                binding.additionalNeedsCreateReservation to R.string.additional_needs_required,
                binding.totalPriceCreateReservation to R.string.total_price_required
            )

            inputs.forEach { (input, errorResId) ->
                isInputEmpty(input, errorResId)
            }

        }

        return view
    }

    private fun isInputEmpty(input: TextInputLayout, errorResId: Int) {
        if (input.editText?.text?.isEmpty() == true) {
            input.error = getString(errorResId)
        } else {
            input.error = null
        }
    }
}