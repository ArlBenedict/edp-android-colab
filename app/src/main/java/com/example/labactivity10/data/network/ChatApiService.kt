package com.example.labactivity10.data.network

import com.example.labactivity10.data.dto.MessageDto
import com.example.labactivity10.data.dto.NewMessageDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatApiService {

    @GET("messages")
    suspend fun getMessages(
        @Query("sortBy") sortBy: String = "createdAt",
        @Query("order") order: String = "desc"
    ): List<MessageDto>

    @POST("messages")
    suspend fun sendMessage(
        @Body message: NewMessageDto
    ): MessageDto
}
