package com.example.myapplication.domain.impl

import com.example.myapplication.data.models.ContactData
import com.example.myapplication.data.remote.request.ContactRequest
import com.example.myapplication.domain.UpdateUseCase
import com.example.myapplication.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class UpdateUseCaseImpl @Inject constructor(
    private var contactRepository: ContactRepository
): UpdateUseCase {
    override fun updateContact(contactData: ContactData): Flow<ResultData<String>> = contactRepository.updateContact(contactData.id,ContactRequest(contactData.name,contactData.phone))
}