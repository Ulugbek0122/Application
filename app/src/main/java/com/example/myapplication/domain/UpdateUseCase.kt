package com.example.myapplication.domain

import com.example.myapplication.data.models.ContactData
import com.example.myapplication.data.remote.request.ContactRequest
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData

interface UpdateUseCase {

    fun updateContact(contactData: ContactData): Flow<ResultData<String>>
}