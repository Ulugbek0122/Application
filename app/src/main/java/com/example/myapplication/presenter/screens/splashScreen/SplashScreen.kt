package com.example.myapplication.presenter.screens.splashScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.presenter.screens.splashScreen.view_model.SplashViewModel
import com.example.myapplication.presenter.screens.splashScreen.view_model.impl.SplashViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.fragment_splash_screen) {
    private val viewModel:SplashViewModel by viewModels<SplashViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.init()
    }
}