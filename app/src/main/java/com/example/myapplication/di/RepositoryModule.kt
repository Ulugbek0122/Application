package com.example.myapplication.di

import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.repository.ContactRepository
import com.example.myapplication.repository.impl.AuthRepositoryImpl
import com.example.myapplication.repository.impl.ContactRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {


    @[Binds Singleton]
    fun authRepository(impl:AuthRepositoryImpl):AuthRepository

    @[Binds Singleton]
    fun contactRepository(impl:ContactRepositoryImpl):ContactRepository
}