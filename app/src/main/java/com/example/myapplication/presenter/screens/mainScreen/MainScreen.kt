package com.example.myapplication.presenter.screens.mainScreen

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainScreenBinding
import com.example.myapplication.presenter.adapters.RvAdapter
import com.example.myapplication.presenter.screens.mainScreen.view_model.MainViewModel
import com.example.myapplication.presenter.screens.mainScreen.view_model.MainViewModelImpl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.fragment_main_screen) {

    private val viewBinding:FragmentMainScreenBinding by viewBinding(FragmentMainScreenBinding::bind)
    private val viewModel:MainViewModel by viewModels<MainViewModelImpl>()
    private val rvAdapter:RvAdapter by lazy { RvAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.messageSharedFlow.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getContacts()

        viewBinding.rvList.adapter = rvAdapter
        lifecycleScope.launch{
            viewModel.listSharedFlow.collectLatest {
                if (it.isEmpty()){
                    viewBinding.placeholder.visibility = View.VISIBLE
                }else{
                    viewBinding.placeholder.visibility = View.GONE
                }
                rvAdapter.submitList(it)
            }
        }


        rvAdapter.setOnEditClickListener {
            viewModel.updateContact(it)
        }

        rvAdapter.setOnDeleteClickListener {
            viewModel.deleteContact(it)
        }

        viewBinding.addContact.setOnClickListener {
            viewModel.openAddScreen()
        }

        viewBinding.btnLogout.setOnClickListener {
            viewModel.logOut()
        }
        viewBinding.btnUnRegister.setOnClickListener{
            viewModel.unregister()
        }


    }
}