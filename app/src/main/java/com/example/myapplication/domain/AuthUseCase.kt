package com.example.myapplication.domain

import com.example.myapplication.data.remote.request.AuthRequest
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData


interface AuthUseCase {
    fun register(authRequest: AuthRequest):Flow<ResultData<String>>
}