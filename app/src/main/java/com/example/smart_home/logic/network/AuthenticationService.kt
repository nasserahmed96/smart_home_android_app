package com.example.smart_home.logic.network

import com.example.smart_home.logic.model.AuthenticationCreds
import retrofit2.Call
import com.example.smart_home.logic.model.AuthenticationResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthenticationService {
    @POST("/api/home_resident/authenticate")
    fun authenticateUser(@Body authenticationCreds: AuthenticationCreds): Call<AuthenticationResponse>
}