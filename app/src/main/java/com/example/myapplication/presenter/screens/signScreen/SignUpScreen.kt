package com.example.myapplication.presenter.screens.signScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.databinding.FragmentSignUpScreenBinding
import com.example.myapplication.presenter.screens.signScreen.view_model.SignUpViewModel
import com.example.myapplication.presenter.screens.signScreen.view_model.SignUpViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpScreen : Fragment(R.layout.fragment_sign_up_screen) {

    private val viewBinding:FragmentSignUpScreenBinding by viewBinding(FragmentSignUpScreenBinding::bind)
    private val viewModel:SignUpViewModel by viewModels<SignUpViewModelImpl>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.messageSharedFlow.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.btnSignUp.setOnClickListener {
            val name = viewBinding.inputNameAuth.text.toString()
            val password = viewBinding.inputPasswordAuth.text.toString()
            viewModel.register(AuthRequest(name, password))
        }

        viewBinding.tvLogIn.setOnClickListener {
            viewModel.openLogIn()
        }
    }
}