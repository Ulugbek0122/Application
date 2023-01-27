package com.example.myapplication.domain.impl

import com.example.myapplication.data.remote.request.ContactRequest
import com.example.myapplication.domain.AddUseCase
import com.example.myapplication.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class AddUseCaseImpl @Inject constructor(
    private var contactRepository: ContactRepository
) : AddUseCase {
    override fun addContact(contactRequest: ContactRequest): Flow<ResultData<String>> = contactRepository.addContact(contactRequest)
}