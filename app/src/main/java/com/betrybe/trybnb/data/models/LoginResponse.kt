package com.betrybe.trybnb.data.models

import javax.security.auth.callback.PasswordCallback

data class LoginResponse(val username: String, val password: String)