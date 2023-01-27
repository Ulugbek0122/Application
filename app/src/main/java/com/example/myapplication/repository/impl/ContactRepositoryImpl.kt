package com.example.myapplication.repository.impl

import com.example.myapplication.data.local.local.LocalStorage
import com.example.myapplication.data.models.ContactData
import com.example.myapplication.data.remote.api.ContactAuth
import com.example.myapplication.data.remote.request.ContactRequest
import com.example.myapplication.data.remote.response.ContactResponse
import com.example.myapplication.repository.ContactRepository
import com.example.myapplication.utils.hasConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import uz.gita.newsappfinaly.utils.MessageData
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private var localStorage: LocalStorage,
    private var contactAuth: ContactAuth
): ContactRepository{


    override fun getContacts(): Flow<ResultData<List<ContactResponse>>> = flow {
        if (hasConnection()){
            val response = contactAuth.getContacts(localStorage.token)
            if (response.isSuccessful){
                val body = response.body()
                if (body != null){
                    val data = body.data
                    emit(ResultData.success(data!!))
                }
            }else{
                emit(ResultData.message(MessageData.messageText(response.errorBody()?.string().toString())))
            }
        }else{
            emit(ResultData.message(MessageData.messageText("No internet")))
        }
    }.catch {
        emit(ResultData.error(it))
    }.flowOn(Dispatchers.IO)




    override fun addContact(contactRequest: ContactRequest): Flow<ResultData<String>> = flow {
        if (hasConnection()) {
            val response = contactAuth.addContact(localStorage.token, contactRequest)
            if (response.isSuccessful){
                val message = response.body()?.message
                emit(ResultData.success(message!!))
            }else{
                emit(ResultData.message(MessageData.messageText(response.errorBody()?.string().toString())))
            }
        }else{
            emit(ResultData.message(MessageData.messageText("No internet")))
        }
    }.catch {
        emit(ResultData.error(it))
    }.flowOn(Dispatchers.IO)




    override fun updateContact(contact_id:Int, contactRequest: ContactRequest): Flow<ResultData<String>> = flow{
        if (hasConnection()){
            val response = contactAuth.updateContact(localStorage.token,contact_id ,contactRequest)
            if (response.isSuccessful){
                val message = response.body()?.message
                emit(ResultData.success(message!!))
            }else{
                emit(ResultData.message(MessageData.messageText(response.errorBody()?.string().toString())))
            }
        }else{
            emit(ResultData.message(MessageData.messageText("No internet")))
        }
    }.catch {
        emit(ResultData.error(it))
    }.flowOn(Dispatchers.IO)



    override fun getContact(contact_Id: Int): Flow<ResultData<ContactResponse>> = flow{
        if (hasConnection()){
            val response = contactAuth.getContact(localStorage.token, contact_Id)
            if (response.isSuccessful){
                val body = response.body()
                if (body != null){
                    val data = body.data
                    if (data != null) {
                        emit(ResultData.success(data))
                    }
                }
            }else{
                emit(ResultData.message(MessageData.messageText(response.errorBody()?.string().toString())))
            }
        }else{
            emit(ResultData.message(MessageData.messageText("No internet")))
        }
    }.catch {
        emit(ResultData.error(it))
    }.flowOn(Dispatchers.IO)




    override fun delete(contact_Id: Int): Flow<ResultData<String>> = flow {
        if (hasConnection()){
            val response = contactAuth.delete(localStorage.token, contact_Id)
            if (response.isSuccessful){
                val message = response.body()?.message
                emit(ResultData.success(message!!))
            }else{
                emit(ResultData.message(MessageData.messageText(response.errorBody()?.string().toString())))
            }
        }else{
            emit(ResultData.message(MessageData.messageText("No internet")))
        }
    }.catch {
        emit(ResultData.error(it))
    }.flowOn(Dispatchers.IO)

}
