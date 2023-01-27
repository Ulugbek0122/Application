package com.example.myapplication.presenter.screens.addScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.request.ContactRequest
import com.example.myapplication.domain.AddUseCase
import com.example.myapplication.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class AddViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val addUseCase: AddUseCase
): AddViewModel,ViewModel(){


    override val messageSharedFlow = MutableSharedFlow<String>()


    override fun addContact(contactRequest: ContactRequest) {
        viewModelScope.launch {
            addUseCase.addContact(contactRequest).collectLatest {
                when(it){
                    is ResultData.Success -> {
                        messageSharedFlow.emit(it.data)
                        navigator.back()
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

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }

    }

}