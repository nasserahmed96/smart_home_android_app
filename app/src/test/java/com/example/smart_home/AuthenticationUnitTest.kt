package com.example.smart_home

import org.junit.Test
import com.example.smart_home.logic.model.AuthenticationCreds
import com.example.smart_home.logic.network.SmartHomeNetwork
import kotlinx.coroutines.runBlocking
import com.example.smart_home.ui.AuthenticateViewModel

class AuthenticationUnitTest {

    @Test
     fun test_login() = runBlocking{
        val authenticationCreds: AuthenticationCreds = AuthenticationCreds(username = "nasser",
        password = "admin")
        val response = SmartHomeNetwork.authenticate(authenticationCreds)
        assert(response.access.isNotEmpty())
    }


}