package com.example.myapplication.presenter.screens.mainScreen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.local.local.LocalStorage
import com.example.myapplication.data.models.ContactData
import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.data.remote.response.ContactResponse
import com.example.myapplication.domain.MainUseCase
import com.example.myapplication.navigation.Navigator
import com.example.myapplication.presenter.screens.mainScreen.MainScreenDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val mainUseCase: MainUseCase,
    private val localStorage: LocalStorage
): MainViewModel,ViewModel(){
    override val messageSharedFlow = MutableSharedFlow<String>()
    override val listSharedFlow = MutableSharedFlow<List<ContactResponse>>()

    override fun getContacts() {
        viewModelScope.launch {
            mainUseCase.getContacts().collectLatest {
                when(it){
                    is ResultData.Success ->{
                        listSharedFlow.emit(it.data)
                    }
                    is ResultData.Message ->{
                        messageSharedFlow.emit(it.message.toString())
                    }
                    is ResultData.Error ->{
                        messageSharedFlow.emit(it.error.message!!)
                    }
                }
            }
        }

    }


    override fun openAddScreen() {
        viewModelScope.launch {
            navigator.navigateTo(MainScreenDirections.actionMainScreenToAddContactScreen())
        }
    }

    override fun logOut() {
        val authRequest = AuthRequest(localStorage.userName,localStorage.userPassword)
        viewModelScope.launch {
            mainUseCase.logOut(authRequest).collectLatest {
                when(it){
                    is ResultData.Success ->{
                        messageSharedFlow.emit(it.data)
                        navigator.navigateTo(MainScreenDirections.actionMainScreenToLoginScreen())
                    }
                    is ResultData.Message ->{
                        messageSharedFlow.emit(it.message.toString())
                    }
                    is ResultData.Error ->{
                        messageSharedFlow.emit(it.error.message!!)
                    }
                }
            }
        }

    }

    override fun unregister() {
        val authRequest = AuthRequest(localStorage.userName,localStorage.userPassword)
        viewModelScope.launch{
            mainUseCase.unregister(authRequest).collectLatest {
                when(it){
                    is ResultData.Success ->{
                        messageSharedFlow.emit(it.data)
                        navigator.navigateTo(MainScreenDirections.actionMainScreenToLoginScreen())
                    }
                    is ResultData.Message ->{
                        messageSharedFlow.emit(it.message.toString())
                    }
                    is ResultData.Error -> {
                        messageSharedFlow.emit(it.error.message!!)
                    }
                }
            }
        }
    }

    override fun updateContact(contactResponse: ContactResponse) {
        viewModelScope.launch {
            navigator.navigateTo(MainScreenDirections.actionMainScreenToUpdateContactScreen(
                ContactData(
                    contactResponse.id,
                    contactResponse.name,
                    contactResponse.phone
                )
            ))
        }

    }

    override fun deleteContact(contactResponse: ContactResponse) {
        viewModelScope.launch {
            mainUseCase.deleteContact(contactResponse.id).collectLatest {
                when(it){
                    is ResultData.Success -> {
                        messageSharedFlow.emit(it.data)
                    }
                    is ResultData.Message -> {
                        messageSharedFlow.emit(it.message.toString())
                    }
                    is ResultData.Error -> {
                        messageSharedFlow.emit(it.error.message!!)
                    }
                }
            }
        }
    }



}