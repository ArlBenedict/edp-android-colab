package com.example.labactivity10.domain

import com.example.labactivity10.core.AppResult

interface ChatRepository {
    suspend fun getMessages(): AppResult<List<Message>>
    suspend fun sendMessage(sender: String, text: String): AppResult<Unit>
}
