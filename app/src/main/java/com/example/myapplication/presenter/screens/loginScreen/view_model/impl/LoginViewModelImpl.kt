package com.example.myapplication.presenter.screens.loginScreen.view_model.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.domain.AuthUseCase
import com.example.myapplication.domain.LoginUseCase
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.presenter.screens.loginScreen.LoginScreenDirections
import com.example.myapplication.presenter.screens.loginScreen.view_model.LoginViewModel
import com.example.myapplication.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val navigator: Navigator
): LoginViewModel, ViewModel(){

    override val loginAuthSharedFlow = MutableSharedFlow<String>()

    override fun login(authRequest: AuthRequest) {
        viewModelScope.launch {
            loginUseCase.login(authRequest).collectLatest {
                when(it){
                    is ResultData.Success ->{
                        loginAuthSharedFlow.emit(it.data)
                        navigator.navigateTo(LoginScreenDirections.actionLoginScreenToMainScreen())
                    }
                    is ResultData.Message ->{
                        loginAuthSharedFlow.emit(it.message.toString())
                    }
                    is ResultData.Error -> {
                        loginAuthSharedFlow.emit(it.error.message.toString())
                    }
                }
            }
        }
    }

    override fun openSignUp() {
        viewModelScope.launch {
            Log.d("RRR", "Salom")
            navigator.navigateTo(LoginScreenDirections.actionLoginScreenToSignUpScreen())
        }
    }


}