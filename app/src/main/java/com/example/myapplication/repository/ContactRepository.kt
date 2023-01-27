package com.example.myapplication.repository

import com.example.myapplication.data.models.ContactData
import com.example.myapplication.data.remote.request.ContactRequest
import com.example.myapplication.data.remote.response.ContactResponse
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData

interface ContactRepository {

    fun getContacts():Flow<ResultData<List<ContactResponse>>>

    fun addContact(contactRequest: ContactRequest):Flow<ResultData<String>>

    fun updateContact(contact_id:Int, contactRequest: ContactRequest):Flow<ResultData<String>>

    fun getContact(contact_Id:Int):Flow<ResultData<ContactResponse>>

    fun delete(contact_Id:Int):Flow<ResultData<String>>
}