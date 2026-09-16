package com.example.labactivity10.ui

import com.example.labactivity10.domain.Message

sealed interface ChatUiState {
    data object Loading : ChatUiState
    data object Empty : ChatUiState
    data class Ready(val messages: List<Message>) : ChatUiState
    data class Error(val message: String) : ChatUiState
}
