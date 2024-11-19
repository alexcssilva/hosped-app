package com.betrybe.trybnb.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.betrybe.trybnb.R
import com.google.android.material.textfield.TextInputLayout

class ProfileFragment: Fragment() {

    private lateinit var emailInput: TextInputLayout
    private lateinit var emailText: String
    private lateinit var passwordInput: TextInputLayout
    private lateinit var passwordText: String
    private lateinit var loginButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        emailInput = view.findViewById(R.id.login_input_profile)
        emailText = emailInput.editText?.text.toString()
        passwordInput = view.findViewById(R.id.password_input_profile)
        passwordText = passwordInput.editText?.text.toString()
        loginButton = view.findViewById(R.id.login_button_profile)

        loginButton.setOnClickListener {
            if (emailText.isEmpty()) emailInput.error = getString(R.string.email_empty_warning)

            if (passwordText.isEmpty()) passwordInput.error =
                getString(R.string.password_empty_warning)
        }
        
        return view
    }
}