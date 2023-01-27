package com.example.myapplication.di

import com.example.myapplication.domain.*
import com.example.myapplication.domain.impl.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {


    @Binds
    fun bindSignUpUseCase(impl:AuthUseCaseImpl):AuthUseCase

    @Binds
    fun bindLoginUseCase(impl:LoginUseCaseImpl):LoginUseCase

    @Binds
    fun bindMainUseCase(impl:MainUseCaseImpl):MainUseCase

    @Binds
    fun bindAddUseCase(impl:AddUseCaseImpl):AddUseCase

    @Binds
    fun bindUpdateUseCase(impl:UpdateUseCaseImpl):UpdateUseCase
}