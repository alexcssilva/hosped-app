package com.project.hospedApp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.project.hospedApp.common.ApiIdlingResource
import com.project.hospedApp.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val loginRepository = LoginRepository()

    private var _token = MutableStateFlow("")
    val token: StateFlow<String>
        get() = _token

    private var _loginFailure = MutableStateFlow(false)
    val failure: StateFlow<Boolean>
        get() = _loginFailure

    fun login(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiIdlingResource.increment()
            val login = loginRepository.login(email, password)
            if (login.success) _token.value = login.data?.token!!
            if (!login.success) _loginFailure.value = true
            ApiIdlingResource.decrement()
        }
    }
}
