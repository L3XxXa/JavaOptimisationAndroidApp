package ru.nsu.malov.lab9.network.serializers

import kotlinx.serialization.Serializable

@Serializable
class SendMoneyRequestSerializer(val toWhom: String, amount: String)
