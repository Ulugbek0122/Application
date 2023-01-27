package com.example.myapplication.repository.impl

import com.example.myapplication.data.local.local.LocalStorage
import com.example.myapplication.data.remote.api.AuthApi
import com.example.myapplication.data.remote.request.AuthRequest
import com.example.myapplication.repository.AuthRepository
import com.example.myapplication.utils.hasConnection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsappfinaly.utils.MessageData
import uz.gita.newsappfinaly.utils.ResultData
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private var authApi: AuthApi,
    private var localStorage: LocalStorage
) : AuthRepository {

    override fun register(authRequest: AuthRequest): Flow<ResultData<String>> = flow {
        if (hasConnection()) {
            val response = authApi.createAccount(authRequest)


            if (response.isSuccessful) {
                localStorage.userName = authRequest.name
                localStorage.userPassword = authRequest.password

                val body = response.body()
                if (body != null) {
                    localStorage.token = body.data?.token.toString()
                    emit(ResultData.success(body.message))
                }
            } else {
                emit(
                    ResultData.message(
                        MessageData.messageText(
                            response.errorBody()?.string().toString()
                        )
                    )
                )
            }
        } else {
            emit(ResultData.message(MessageData.messageText("No Internet")))
        }
    }
        .catch {
            emit(ResultData.error(it))
        }
        .flowOn(Dispatchers.IO)


    override fun unRegister(authRequest: AuthRequest): Flow<ResultData<String>> = flow {
        if (hasConnection()) {
            val response = authApi.deleteAccount(authRequest)
            if (response.isSuccessful) {
                localStorage.userName = ""
                localStorage.userPassword = ""
                localStorage.token = ""
                val body = response.body()
                if (body != null) {
                    emit(ResultData.success(body.message.toString()))
                }
            } else {
                emit(ResultData.message(MessageData.messageText(response.errorBody().toString())))
            }
        }else{
            emit(ResultData.message(MessageData.messageText("No internet")))
        }
    }.catch {
        emit(ResultData.error(it))
    }.flowOn(Dispatchers.IO)

    override fun logIn(authRequest: AuthRequest): Flow<ResultData<String>> = flow {
        if (hasConnection()){
            val response = authApi.login(authRequest)
            if (response.isSuccessful){
                localStorage.userName = authRequest.name
                localStorage.userPassword = authRequest.password
                val resultData = response.body()
                if (resultData != null) {
                    localStorage.token = resultData.data?.token.toString()
                    emit(ResultData.success(resultData.message))
                }

            }else{
                emit(ResultData.message(MessageData.messageText(response.errorBody()?.string().toString())))
            }
        }else{
            emit(ResultData.message(MessageData.messageText("No Internet")))
        }
    }
        .catch {
            emit(ResultData.error(it))
        }.flowOn(Dispatchers.IO)

    override fun logOut(authRequest: AuthRequest): Flow<ResultData<String>> = flow {
        if (hasConnection()){
            val response = authApi.logOut(authRequest)
            if (response.isSuccessful){
                localStorage.userName = ""
                localStorage.userPassword = ""
                localStorage.token = ""
                val body = response.body()
                if (body != null){
                    emit(ResultData.success(body.message))
                }
            }else{
                emit(ResultData.message(MessageData.messageText(response.errorBody()?.string().toString())))
            }
        }
    }

}