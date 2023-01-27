package com.example.myapplication.data.local.local

import android.content.Context

import com.example.myapplication.utils.SharedPreference

class LocalStorage(context: Context) :SharedPreference(context){
    var token:String by StringPreference("")
    var userName:String by StringPreference("")
    var userPassword:String by StringPreference("")
}