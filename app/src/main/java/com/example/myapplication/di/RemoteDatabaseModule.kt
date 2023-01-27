package com.example.myapplication.di

import android.content.Context
import com.chuckerteam.chucker.api.Chucker
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.myapplication.data.local.local.LocalStorage
import com.example.myapplication.data.remote.api.AuthApi
import com.example.myapplication.data.remote.api.ContactAuth
import com.example.myapplication.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDatabaseModule {


    @Provides
    fun client(@ApplicationContext context: Context) =
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .build()

    @[Provides Singleton]
    fun provideRetrofit(client: OkHttpClient):Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @[Provides Singleton]
    fun provideAuthApi(retrofit:Retrofit):AuthApi = retrofit.create(AuthApi::class.java)

    @[Provides Singleton]
    fun provideContactAuthApi(retrofit: Retrofit):ContactAuth = retrofit.create(ContactAuth::class.java)

    @[Provides Singleton]
    fun provideSharedPref(@ApplicationContext context: Context):LocalStorage =LocalStorage(context)
}