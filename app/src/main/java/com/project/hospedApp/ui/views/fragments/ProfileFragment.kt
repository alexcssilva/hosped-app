package com.project.hospedApp.ui.views.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.hospedApp.R
import com.project.hospedApp.databinding.FragmentProfileBinding
import com.project.hospedApp.ui.viewmodels.ProfileViewModel
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileVM: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.bind(view)

        clearInput()

        binding.loginButtonProfile.setOnClickListener {
            val loginInput = binding.loginInputProfile
            val passInput = binding.passwordInputProfile

            val loginText = loginInput.editText?.text.toString()
            val passText = passInput.editText?.text.toString()

            // Verifica se os campos estão vazios
            var hasError = false

            if (loginText.isEmpty()) {
                loginInput.error = getString(R.string.email_empty_warning)
                hasError = true
            }

            if (passText.isEmpty()) {
                passInput.error = getString(R.string.password_empty_warning)
                hasError = true
            }

            // Se não tiver erros, faz o login e mostra mensagem de sucesso
            if (!hasError) {
                profileVM.login(loginText, passText)

                Snackbar.make(
                    binding.profileScrollView,
                    getString(R.string.login_success),
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        return view
    }

    private fun clearInput() {
        val textInputLayouts = listOf(
            binding.loginInputProfile,
            binding.passwordInputProfile
        )

        textInputLayouts.forEach { input ->
            input.editText?.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    input.error = null
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }
}