package com.example.labactivity10.data.repository

import com.example.labactivity10.core.AppResult
import com.example.labactivity10.data.dto.NewMessageDto
import com.example.labactivity10.data.dto.toDomain
import com.example.labactivity10.data.network.ChatApiService
import com.example.labactivity10.domain.ChatRepository
import com.example.labactivity10.domain.Message
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ChatRepositoryImpl(
    private val api: ChatApiService
) : ChatRepository {

    private inline fun <T> safeCall(block: () -> T): AppResult<T> =
        try {
            AppResult.Success(block())
        } catch (e: UnknownHostException) {
            AppResult.Failure.NoInternet
        } catch (e: SocketTimeoutException) {
            AppResult.Failure.Timeout
        } catch (e: IOException) {
            AppResult.Failure.NoInternet
        } catch (e: Exception) {
            AppResult.Failure.Unknown(e.message)
        }

    override suspend fun getMessages(): AppResult<List<Message>> =
        safeCall {
            api.getMessages().toDomain()
        }

    override suspend fun sendMessage(
        sender: String,
        text: String
    ): AppResult<Unit> =
        safeCall {
            val dto = NewMessageDto(
                sender = sender,
                text = text,
                createdAt = System.currentTimeMillis()
            )
            api.sendMessage(dto)
            Unit
        }
}
