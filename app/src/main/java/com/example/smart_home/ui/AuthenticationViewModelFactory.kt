package com.example.smart_home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuthenticationViewModelFactory: ViewModelProvider.Factory {
    override fun<T: ViewModel> create (modelClass: Class<T>): T{
        return AuthenticateViewModel() as T
    }
}