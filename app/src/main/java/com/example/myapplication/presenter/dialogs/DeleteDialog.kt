package com.example.myapplication.presenter.dialogs

import android.app.AlertDialog
import android.content.Context
import com.example.myapplication.databinding.DeleteDialogBinding

class DeleteDialog(context: Context):AlertDialog(context) {

    private lateinit var binding:DeleteDialogBinding
    private var deleteListener:(() -> Unit)? = null

    fun setOnDeleteClickListener(l:() -> Unit){
        deleteListener = l
    }
}