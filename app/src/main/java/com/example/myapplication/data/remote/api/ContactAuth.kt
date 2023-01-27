package com.example.myapplication.data.remote.api

import com.example.myapplication.data.models.ContactData
import com.example.myapplication.data.remote.request.ContactRequest
import com.example.myapplication.data.remote.response.BaseResponse
import com.example.myapplication.data.remote.response.ContactResponse
import retrofit2.Response
import retrofit2.http.*

interface ContactAuth {

    @GET("contact")
    suspend fun getContacts (@Header("token") token:String):Response<BaseResponse<List<ContactResponse>>>

    @POST("contact")
    suspend fun addContact(@Header("token") token:String, @Body contactRequest: ContactRequest):Response<BaseResponse<ContactResponse>>

    @PUT("contact")
    suspend fun updateContact(@Header("token")token:String, @Query("id") id:Int, @Body contactRequest: ContactRequest):Response<BaseResponse<ContactResponse>>

    @GET("contact/{id}")
    suspend fun getContact(@Header("token")token: String,@Path("id")id:Int):Response<BaseResponse<ContactResponse>>

    @DELETE("contact")
    suspend fun delete(@Header("token")token: String, @Query("id") id: Int):Response<BaseResponse<ContactResponse>>
}