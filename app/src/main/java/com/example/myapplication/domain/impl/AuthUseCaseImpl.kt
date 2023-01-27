package com.example.myapplication.domain.impl

import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.domain.AuthUseCase
import com.example.myapplication.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(
    private var authRepository: AuthRepository
): AuthUseCase{

    override fun register(authRequest: AuthRequest):Flow<ResultData<String>> = authRepository.register(authRequest)

}