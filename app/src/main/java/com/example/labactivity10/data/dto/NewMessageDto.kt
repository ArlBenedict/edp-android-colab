package com.example.labactivity10.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewMessageDto(
    val sender: String,
    val text: String,
    val createdAt: Long
)
