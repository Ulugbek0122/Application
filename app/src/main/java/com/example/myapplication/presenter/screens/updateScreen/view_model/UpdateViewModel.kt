package com.example.myapplication.presenter.screens.updateScreen.view_model

import com.example.myapplication.data.models.ContactData
import com.example.myapplication.data.remote.request.ContactRequest
import kotlinx.coroutines.flow.SharedFlow

interface UpdateViewModel {
    val messageSharedFlow:SharedFlow<String>

    fun updateContact(contactData: ContactData)

    fun back()
}