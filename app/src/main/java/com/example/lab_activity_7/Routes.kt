package com.example.lab_activity_7

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Greeting(
    val userName: String
)