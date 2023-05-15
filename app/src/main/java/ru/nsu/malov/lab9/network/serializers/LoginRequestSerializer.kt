package ru.nsu.malov.lab9.network.serializers

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestSerializer(val login: String, val password: String)
