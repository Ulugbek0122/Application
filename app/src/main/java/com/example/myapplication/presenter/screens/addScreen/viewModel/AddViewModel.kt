package com.example.myapplication.presenter.screens.addScreen.viewModel

import com.example.myapplication.data.remote.request.ContactRequest
import kotlinx.coroutines.flow.SharedFlow

interface AddViewModel {
    val messageSharedFlow:SharedFlow<String>

    fun addContact(contactRequest: ContactRequest)

    fun back()
}