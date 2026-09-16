package com.example.labactivity10.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.labactivity10.core.AppResult
import com.example.labactivity10.data.network.NetworkModule
import com.example.labactivity10.data.repository.ChatRepositoryImpl
import com.example.labactivity10.domain.ChatRepository
import kotlinx.coroutines.launch

class ChatViewModel(
    private val repository: ChatRepository
) : ViewModel() {

    var uiState by mutableStateOf<ChatUiState>(ChatUiState.Loading)
        private set

    var myName by mutableStateOf("")
    var draft by mutableStateOf("")

    init {
        load()
    }

    fun load() {
        uiState = ChatUiState.Loading
        viewModelScope.launch {
            when (val result = repository.getMessages()) {
                is AppResult.Success -> {
                    if (result.data.isEmpty()) {
                        uiState = ChatUiState.Empty
                    } else {
                        uiState = ChatUiState.Ready(result.data)
                    }
                }
                is AppResult.Failure.NoInternet -> {
                    uiState = ChatUiState.Error("No internet connection.")
                }
                is AppResult.Failure.Timeout -> {
                    uiState = ChatUiState.Error("The server took too long.")
                }
                is AppResult.Failure.Unknown -> {
                    uiState = ChatUiState.Error("Something went wrong.")
                }
            }
        }
    }

    fun send() {
        if (myName.isBlank() || draft.isBlank()) return

        viewModelScope.launch {
            when (repository.sendMessage(myName, draft)) {
                is AppResult.Success -> {
                    draft = ""
                    load()
                }
                is AppResult.Failure -> {
                    uiState = ChatUiState.Error("Could not send. Check your connection.")
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                ChatViewModel(
                    ChatRepositoryImpl(NetworkModule.chatApi)
                )
            }
        }
    }
}
