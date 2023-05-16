package ru.nsu.malov.lab9.repository

import dagger.Module
import dagger.Provides


@Module
class BalanceRepositoryModule{
    @Provides
    fun getBalanceRepository(): GetBalanceRepository {
        return GetBalanceRepository()
    }
}
class GetBalanceRepository {
}