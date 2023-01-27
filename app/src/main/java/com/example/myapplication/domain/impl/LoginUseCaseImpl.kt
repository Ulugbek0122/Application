package com.example.myapplication.domain.impl

import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.domain.LoginUseCase
import com.example.myapplication.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private var authRepository: AuthRepository
): LoginUseCase {
    override fun login(authRequest: AuthRequest): Flow<ResultData<String>>  = authRepository.logIn(authRequest)
}