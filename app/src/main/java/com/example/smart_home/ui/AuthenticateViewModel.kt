package com.example.smart_home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.smart_home.logic.Repository
import com.example.smart_home.logic.model.AuthenticationCreds
import com.example.smart_home.logic.model.AuthenticationResponse
import com.example.smart_home.logic.network.SmartHomeNetwork

class AuthenticateViewModel: ViewModel() {
   val authenticationResponse: LiveData<AuthenticationResponse>
           get() = _authenticationResponse
    private val _authenticationResponse = MutableLiveData<AuthenticationResponse>()
    private val authenticationCredsLiveData = MutableLiveData<AuthenticationCreds>()


    val response = Transformations.switchMap(authenticationCredsLiveData){
        autheCreds -> Repository.authenticateUser(autheCreds)
    }

    fun authenticateUser(_authenticationCreds: AuthenticationCreds){
        authenticationCredsLiveData.value = _authenticationCreds
    }
}