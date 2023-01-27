package com.example.myapplication.presenter.screens.loginScreen

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.databinding.FragmentLoginScreenBinding
import com.example.myapplication.presenter.screens.loginScreen.view_model.LoginViewModel
import com.example.myapplication.presenter.screens.loginScreen.view_model.impl.LoginViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginScreen : Fragment(R.layout.fragment_login_screen) {

    private val viewModel:LoginViewModel by viewModels<LoginViewModelImpl>()
    private val viewBinding:FragmentLoginScreenBinding by viewBinding(FragmentLoginScreenBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.loginAuthSharedFlow.collectLatest{
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.signUp.setOnClickListener {
            Toast.makeText(requireContext(), "Salom", Toast.LENGTH_SHORT).show()
            viewModel.openSignUp()
        }

        viewBinding.btnLogIn.setOnClickListener { 
            val name = viewBinding.inputNameAuth.text.toString()
            val password = viewBinding.inputPasswordAuth.text.toString()
            viewModel.login(AuthRequest(name,password))
        }
    }
}