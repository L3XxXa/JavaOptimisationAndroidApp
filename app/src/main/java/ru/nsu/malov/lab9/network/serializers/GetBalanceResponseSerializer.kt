package ru.nsu.malov.lab9.network.serializers

import kotlinx.serialization.Serializable

@Serializable
data class GetBalanceResponseSerializer(val balance: Int)
