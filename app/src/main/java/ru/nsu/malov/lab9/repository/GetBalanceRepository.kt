package ru.nsu.malov.lab9.repository

import dagger.Module
import dagger.Provides
import ru.nsu.malov.lab9.network.api.Api
import ru.nsu.malov.lab9.network.serializers.GetBalanceResponseSerializer


@Module
class BalanceRepositoryModule{
    @Provides
    fun getBalanceRepository(): GetBalanceRepository {
        return GetBalanceRepository()
    }
}
class GetBalanceRepository {
    suspend fun getBalance(token: String): GetBalanceResponseSerializer{
        return Api.retrofitService.getBalance(token)
    }
}