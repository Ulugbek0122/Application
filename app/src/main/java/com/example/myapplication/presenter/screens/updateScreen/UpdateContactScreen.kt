package com.example.myapplication.presenter.screens.updateScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.data.models.ContactData
import com.example.myapplication.databinding.FragmentUpdateContactScreenBinding
import com.example.myapplication.presenter.screens.updateScreen.view_model.UpdateViewModel
import com.example.myapplication.presenter.screens.updateScreen.view_model.UpdateViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateContactScreen : Fragment(R.layout.fragment_update_contact_screen) {
    private val viewBinding:FragmentUpdateContactScreenBinding by viewBinding(FragmentUpdateContactScreenBinding::bind)
    private val viewModel:UpdateViewModel by viewModels<UpdateViewModelImpl>()

    private val args:UpdateContactScreenArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch{
            viewModel.messageSharedFlow.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val contactData = args.contactData
        viewBinding.inputName.setText(contactData.name)
        viewBinding.inputPhone.setText(contactData.phone)

        viewBinding.btnUpdateContact.setOnClickListener {
            val name = viewBinding.inputName.text.toString()
            val phone = viewBinding.inputPhone.text.toString()
            viewModel.updateContact(ContactData(contactData.id,name,phone))
        }
        viewBinding.backBtn.setOnClickListener {
            viewModel.back()
        }
    }
}