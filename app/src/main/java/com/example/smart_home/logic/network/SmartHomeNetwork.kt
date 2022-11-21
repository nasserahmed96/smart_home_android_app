package com.example.smart_home.logic.network

import android.util.Log
import com.example.smart_home.logic.model.AuthenticationCreds
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SmartHomeNetwork {
    private val authenticationService = ServiceCreator.create(AuthenticationService::class.java)
    suspend fun authenticate(authenticationCreds: AuthenticationCreds) = authenticationService.authenticateUser(authenticationCreds).await()
    private suspend fun <T> Call<T>.await(): T {

        return suspendCoroutine { continuation ->
            enqueue(object: Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    Log.d("SMART_HOME_NETWOR: ", "CALL_AWAIT_ON_RESPONSE")
                    Log.d("SMART_HOME_NETWORK", response.body().toString())
                    val body = response.body()
                    if(body != null) continuation.resume(body)
                    else continuation.resumeWithException( java.lang.RuntimeException("Response body is null"))

                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    Log.d("SMART_HOME_NETWORK", "ON_FAILURE")
                    Log.d("SMART_HOME_NETWORK", t.toString())
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}