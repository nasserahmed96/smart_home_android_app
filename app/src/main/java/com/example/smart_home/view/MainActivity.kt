package com.example.smart_home.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.smart_home.ui.AuthenticateViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.smart_home.view.HomeActivity
import com.example.smart_home.databinding.ActivityMainBinding
import com.example.smart_home.logic.model.AuthenticationCreds
import com.example.smart_home.logic.model.AuthenticationResponse
import com.example.smart_home.ui.AuthenticationViewModelFactory



class MainActivity : AppCompatActivity() {
    lateinit var authenticateViewModel: AuthenticateViewModel
    lateinit var sp: SharedPreferences
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sp = getPreferences(Context.MODE_PRIVATE)
        authenticateViewModel = ViewModelProviders.of(this, AuthenticationViewModelFactory()).get(AuthenticateViewModel::class.java)
        binding.buttonLogin.setOnClickListener{
            val authenticationCreds = AuthenticationCreds(username = binding.edEmailormobileLogin.text.toString(),
            password = binding.edPasswordLogin.text.toString())
            authenticateViewModel.authenticateUser(authenticationCreds)
        }
        authenticateViewModel.response.observe(this, Observer{
            response ->
            Log.d("Authentication Response", response.getOrNull()?.access.toString())
            response.getOrNull()?.let { onSuccessLogin(it) }
        })
    }

    private fun onSuccessLogin(response: AuthenticationResponse){
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("UserName: ", "Nasser")
        startActivity(intent)
    }
}
