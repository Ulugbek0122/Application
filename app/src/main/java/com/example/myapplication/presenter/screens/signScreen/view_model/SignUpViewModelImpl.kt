package com.example.myapplication.presenter.screens.signScreen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.domain.AuthUseCase
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.presenter.screens.signScreen.SignUpScreenDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class SignUpViewModelImpl @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val navigator: Navigator
): SignUpViewModel,ViewModel(){

    override val messageSharedFlow = MutableSharedFlow<String>()


    override fun register(authRequest: AuthRequest) {
        viewModelScope.launch {
            authUseCase.register(authRequest).collectLatest {
                when(it){
                    is ResultData.Success->{
                        messageSharedFlow.emit(it.data)
                        navigator.navigateTo(SignUpScreenDirections.actionSignUpScreenToMainScreen())
                    }
                    is ResultData.Message ->{
                        messageSharedFlow.emit(it.message.toString())
                    }
                    is ResultData.Error ->{
                        messageSharedFlow.emit(it.error.message.toString())
                    }
                }
            }
        }

    }

    override fun openLogIn() {
        viewModelScope.launch {
            navigator.back()
        }

    }


}