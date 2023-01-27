package com.example.myapplication.data.remote.response

data class BaseResponse<T>(
    val message:String,
    val data:T?
)
