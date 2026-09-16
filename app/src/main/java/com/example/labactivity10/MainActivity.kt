package com.example.labactivity10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.labactivity10.ui.ChatScreen
import com.example.labactivity10.ui.theme.LabActivity10Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabActivity10Theme {
                ChatScreen()
            }
        }
    }
}
