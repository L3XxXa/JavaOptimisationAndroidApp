package ru.nsu.malov.lab9.repository

import dagger.Module
import dagger.Provides
import ru.nsu.malov.lab9.network.api.Api
import ru.nsu.malov.lab9.network.serializers.LoginRequestSerializer
import ru.nsu.malov.lab9.network.serializers.LoginResponseSerializer

@Module
class LoginRepositoryModule {
    @Provides
    fun loginRepository(): LoginRepository{
        return LoginRepository()
    }
}

class LoginRepository{
    suspend fun authUser(loginRequestSerializer: LoginRequestSerializer): LoginResponseSerializer{
        return Api.retrofitService.authUser(loginRequestSerializer)
    }
}