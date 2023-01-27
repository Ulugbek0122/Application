package com.example.myapplication.presenter.screens.updateScreen.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.ContactData
import com.example.myapplication.domain.UpdateUseCase
import com.example.myapplication.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class UpdateViewModelImpl @Inject constructor(
    private val navigator: Navigator,
    private val updateUseCase: UpdateUseCase
): UpdateViewModel, ViewModel(){

    override val messageSharedFlow = MutableSharedFlow<String>()

    override fun updateContact(contactData: ContactData) {
        viewModelScope.launch {
            updateUseCase.updateContact(contactData).collectLatest {
                when(it){
                    is ResultData.Success ->{
                        messageSharedFlow.emit(it.data)
                        navigator.back()
                    }
                    is ResultData.Message -> {
                        messageSharedFlow.emit(it.message.toString())
                    }
                    is ResultData.Error ->{
                        messageSharedFlow.emit(it.error.toString())
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