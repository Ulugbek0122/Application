package com.example.myapplication.presenter.screens.loginScreen.view_model

import com.example.myapplication.data.remote.request.AuthRequest
import kotlinx.coroutines.flow.SharedFlow

interface LoginViewModel {

    val loginAuthSharedFlow:SharedFlow<String>


    fun  login(authRequest: AuthRequest)

    fun openSignUp()
}