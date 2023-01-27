package com.example.myapplication.presenter.screens.splashScreen.view_model.impl

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.local.LocalStorage
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.presenter.screens.splashScreen.SplashScreenDirections
import com.example.myapplication.presenter.screens.splashScreen.view_model.SplashViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private var navigator: Navigator,
    private var localStorage: LocalStorage
): SplashViewModel,ViewModel(){

    override fun init(){
        viewModelScope.launch {
            Log.d("TTT","AAA")
            delay(2000)
            Log.d("TTT","BBB")
            if (localStorage.token == ""){
                Log.d("TTT","CCC")
                navigator.navigateTo(SplashScreenDirections.actionSplashScreenToLoginScreen())
            }else{
                Log.d("TTT","DDD")
                navigator.navigateTo(SplashScreenDirections.actionSplashScreenToMainScreen())
            }
        }
    }
}