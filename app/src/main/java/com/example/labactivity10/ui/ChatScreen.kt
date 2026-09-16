package com.example.labactivity10.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.labactivity10.domain.Message

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    viewModel: ChatViewModel = viewModel(factory = ChatViewModel.Factory)
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LiceoChat") },
                actions = {
                    IconButton(onClick = { viewModel.load() }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        },
        bottomBar = {
            MessageInput(viewModel)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when (val state = viewModel.uiState) {
                is ChatUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is ChatUiState.Empty -> {
                    Text(
                        text = "No messages yet. Say hello!",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is ChatUiState.Ready -> {
                    LazyColumn(
                        Modifier.fillMaxSize()
                    ) {
                        items(
                            state.messages,
                            key = { it.id }
                        ) {
                            MessageRow(it)
                        }
                    }
                }
                is ChatUiState.Error -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(state.message, color = MaterialTheme.colorScheme.error)
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(onClick = { viewModel.load() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MessageRow(message: Message) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            message.sender,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            message.text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
    HorizontalDivider()
}

@Composable
fun MessageInput(viewModel: ChatViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .imePadding()
    ) {
        OutlinedTextField(
            value = viewModel.myName,
            onValueChange = { viewModel.myName = it },
            label = { Text("Your full name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = viewModel.draft,
                onValueChange = { viewModel.draft = it },
                label = { Text("Message") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { viewModel.send() },
                enabled = viewModel.myName.isNotBlank() && viewModel.draft.isNotBlank()
            ) {
                Text("Send")
            }
        }
    }
}
