package com.example.myapplication.repository

import com.example.myapplication.data.remote.request.AuthRequest
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData

interface AuthRepository {

    fun register(authRequest: AuthRequest):Flow<ResultData<String>>
    fun unRegister(authRequest: AuthRequest):Flow<ResultData<String>>
    fun logIn(authRequest: AuthRequest):Flow<ResultData<String>>
    fun logOut(authRequest: AuthRequest):Flow<ResultData<String>>
}