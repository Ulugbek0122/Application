package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.data.remote.response.AuthResponse
import com.example.myapplication.data.remote.response.BaseResponse
import retrofit2.Response

import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.newsappfinaly.utils.ResultData

interface AuthApi {

    @POST("register")
    suspend fun createAccount(@Body authRequest: AuthRequest): Response<BaseResponse<AuthResponse>>

    @POST("unregister")
    suspend fun deleteAccount(@Body authRequest: AuthRequest):Response<BaseResponse<AuthResponse>>

    @POST("login")
    suspend fun login(@Body authRequest: AuthRequest):Response<BaseResponse<AuthResponse>>

    @POST("logout")
    suspend fun logOut(@Body authRequest: AuthRequest):Response<BaseResponse<AuthResponse>>
}