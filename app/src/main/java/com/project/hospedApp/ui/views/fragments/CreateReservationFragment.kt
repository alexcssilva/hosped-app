package com.project.hospedApp.ui.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.project.hospedApp.R
import com.project.hospedApp.data.models.Booking
import com.project.hospedApp.data.models.BookingDates
import com.project.hospedApp.databinding.FragmentCreateReservationBinding
import com.project.hospedApp.ui.viewmodels.CreateReservationViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class CreateReservationFragment : Fragment() {
    private lateinit var binding: FragmentCreateReservationBinding
    private val viewModel: CreateReservationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_create_reservation, container, false)
        binding = FragmentCreateReservationBinding.bind(view)

        clearInput()

        binding.createReservationButton.setOnClickListener {
            var readyToPost = true
            val reservationLinearLayout: ViewGroup =
                view.findViewById(R.id.reservation_linear_layout)
            for (i in 0 until reservationLinearLayout.childCount) {
                val child = reservationLinearLayout.getChildAt(i)
                if (child !is TextInputLayout) continue
                isInputEmpty(child)
                if (child.error != null) {
                    readyToPost = false
                }
            }

            if (readyToPost) {
                val checkin =
                    reformatDate(binding.checkinCreateReservation.editText?.text.toString())
                val checkout =
                    reformatDate(binding.checkoutCreateReservation.editText?.text.toString())
                lateinit var bookingDates: BookingDates
                if (checkin != null && checkout != null) {
                    bookingDates = BookingDates(checkin, checkout)

                    val body = Booking(
                        firstname = binding.firstNameCreateReservation.editText?.text.toString(),
                        lastname = binding.lastNameCreateReservation.editText?.text.toString(),
                        totalprice = binding.totalPriceCreateReservation.editText?.text.toString()
                            .toInt(),
                        depositpaid = binding.depositpaidCreateReservation.isChecked,
                        bookingdates = bookingDates,
                        additionalneeds = binding.additionalNeedsCreateReservation
                            .editText?.text.toString()
                    )
                    viewModel.createBooking(body)
                }
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel.isBookingCreationSuccess.collect { success ->
                        if (success) {
                            Snackbar.make(
                                binding.createReservationScrollView,
                                getString(R.string.booking_success),
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

        return binding.root
    }

    private fun clearInput() {
        val textInputLayouts = listOf(
            binding.firstNameCreateReservation,
            binding.lastNameCreateReservation,
            binding.totalPriceCreateReservation,
            binding.checkinCreateReservation,
            binding.checkoutCreateReservation,
            binding.additionalNeedsCreateReservation
        )

        textInputLayouts.forEach { input ->
            input.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    input.error = null // Limpa a mensagem de erro
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun isInputEmpty(input: TextInputLayout) {
        if (input.editText?.text?.isEmpty() == true) {
            input.error =
                "O campo ${input.hint.toString()
                    .replace(" do hóspede", "")
                    .replace("adicionais", "Adicionais")
                    .replace("total", "Total")
                } é obrigatório"
        } else {
            input.error = null
        }
    }

    private fun reformatDate(inputDate: String): String? {
        val inputFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        return try {
            val date = inputFormatter.parse(inputDate)
            if (date != null) {
                outputFormatter.format(date)
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}
