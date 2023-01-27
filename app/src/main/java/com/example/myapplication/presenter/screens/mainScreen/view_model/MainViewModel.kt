package com.example.myapplication.presenter.screens.mainScreen.view_model

import com.example.myapplication.data.remote.response.ContactResponse
import kotlinx.coroutines.flow.SharedFlow

interface MainViewModel {

    val messageSharedFlow:SharedFlow<String>
    val listSharedFlow:SharedFlow<List<ContactResponse>>

    fun getContacts()

    fun openAddScreen()

    fun logOut()

    fun unregister()

    fun updateContact(contactResponse: ContactResponse)

    fun deleteContact(contactResponse: ContactResponse)


}