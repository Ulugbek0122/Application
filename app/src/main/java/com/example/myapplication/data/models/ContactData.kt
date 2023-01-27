package com.example.myapplication.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactData(
    val id:Int,
    val name:String,
    val phone:String
):Parcelable
