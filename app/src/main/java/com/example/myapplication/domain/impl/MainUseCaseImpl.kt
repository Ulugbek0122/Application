package com.example.myapplication.domain.impl

import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.data.remote.response.ContactResponse
import com.example.myapplication.domain.MainUseCase
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private var authRepository: AuthRepository,
    private var contactRepository: ContactRepository
): MainUseCase {


    override fun unregister(authRequest: AuthRequest): Flow<ResultData<String>> = authRepository.unRegister(authRequest)

    override fun logOut(authRequest: AuthRequest): Flow<ResultData<String>> = authRepository.logOut(authRequest)

    override fun getContacts(): Flow<ResultData<List<ContactResponse>>>  = contactRepository.getContacts()

    override fun deleteContact(id:Int): Flow<ResultData<String>> = contactRepository.delete(id)
    
    override fun getContact(id: Int): Flow<ResultData<ContactResponse>> = contactRepository.getContact(id)
}