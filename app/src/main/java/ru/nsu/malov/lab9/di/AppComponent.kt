package ru.nsu.malov.lab9.di

import dagger.Component
import ru.nsu.malov.lab9.repository.BalanceRepositoryModule
import ru.nsu.malov.lab9.repository.GetBalanceRepository
import ru.nsu.malov.lab9.repository.LoginRepository
import ru.nsu.malov.lab9.repository.LoginRepositoryModule

@Component(modules = [LoginRepositoryModule::class, BalanceRepositoryModule::class])
interface AppComponent {
    fun getLoginRepository(): LoginRepository
    fun getBalanceRepository(): GetBalanceRepository
}

