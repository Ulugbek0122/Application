package com.example.myapplication.domain

import com.example.myapplication.data.remote.request.ContactRequest
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData

interface AddUseCase {
    fun addContact(contactRequest: ContactRequest):Flow<ResultData<String>>
}