package ru.nsu.malov.lab9.repository

import dagger.Module
import dagger.Provides
import ru.nsu.malov.lab9.network.api.Api
import ru.nsu.malov.lab9.network.serializers.SendMoneyRequestSerializer
import ru.nsu.malov.lab9.network.serializers.SendMoneyResponseSerializer

@Module
class SendMoneyModule{
    @Provides
    fun sendMoneyRepository(): SendMoneyRepository{
        return SendMoneyRepository()
    }
}

class SendMoneyRepository {
    suspend fun sendMoney(sendMoneyRequestSerializer: SendMoneyRequestSerializer, token: String): SendMoneyResponseSerializer{
        return Api.retrofitService.sendMoney(token, sendMoneyRequestSerializer)
    }
}