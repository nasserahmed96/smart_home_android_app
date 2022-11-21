package com.example.smart_home.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.example.smart_home.logic.model.AuthenticationCreds
import com.example.smart_home.logic.model.AuthenticationResponse
import com.example.smart_home.logic.network.SmartHomeNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object Repository {


    fun authenticateUser(authenticationCreds: AuthenticationCreds) = fire(Dispatchers.IO){
            val authenticationResponse = SmartHomeNetwork.authenticate(authenticationCreds)
            if (authenticationResponse.access.isNotEmpty()){
                Result.success(authenticationResponse)
            }
            else{
                Result.failure(java.lang.RuntimeException("Login failed please check username and password"))
            }
        }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context){
            val result = try{
                block()
            }catch (e: Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }
}