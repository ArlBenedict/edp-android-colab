package com.example.labactivity10.data.dto

import com.example.labactivity10.domain.Message

fun MessageDto.toDomain(): Message = Message(
    id = id ?: "",
    sender = sender ?: "Unknown",
    text = text ?: "",
    createdAt = createdAt ?: 0L
)

fun List<MessageDto>.toDomain(): List<Message> =
    map { it.toDomain() }
