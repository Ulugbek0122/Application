package com.example.myapplication.presenter.screens.addScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.data.remote.request.ContactRequest
import com.example.myapplication.databinding.FragmentAddContactScreenBinding
import com.example.myapplication.presenter.screens.addScreen.viewModel.AddViewModel
import com.example.myapplication.presenter.screens.addScreen.viewModel.AddViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddContactScreen : Fragment(R.layout.fragment_add_contact_screen) {
    private val viewBinding:FragmentAddContactScreenBinding by viewBinding(FragmentAddContactScreenBinding::bind)
    private val viewModel:AddViewModel by viewModels<AddViewModelImpl>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch{
            viewModel.messageSharedFlow.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.btnAddContact.setOnClickListener {
            var name = viewBinding.inputName.text.toString()
            var phone = viewBinding.inputPhone.text.toString()
            viewModel.addContact(ContactRequest(name, phone))
        }

        viewBinding.backBtn.setOnClickListener {
            viewModel.back()
        }
    }
}