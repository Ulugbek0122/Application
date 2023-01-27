package com.example.myapplication.domain

import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.data.remote.response.ContactResponse
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData

interface MainUseCase {

    fun unregister(authRequest: AuthRequest):Flow<ResultData<String>>

    fun logOut(authRequest: AuthRequest):Flow<ResultData<String>>

    fun getContacts():Flow<ResultData<List<ContactResponse>>>

    fun deleteContact(id:Int):Flow<ResultData<String>>

    fun getContact(id:Int):Flow<ResultData<ContactResponse>>
}