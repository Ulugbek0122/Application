package com.example.myapplication.presenter.screens.signScreen.view_model

import com.example.myapplication.data.remote.request.AuthRequest
import kotlinx.coroutines.flow.SharedFlow

interface SignUpViewModel {
    val messageSharedFlow:SharedFlow<String>

    fun register(authRequest: AuthRequest)

    fun openLogIn()
}