package ru.nsu.malov.lab9

import dagger.Module
import ru.nsu.malov.lab9.network.api.Api
import ru.nsu.malov.lab9.network.serializers.LoginRequestSerializer
import ru.nsu.malov.lab9.network.serializers.LoginResponseSerializer

@Module
class LoginRepository {
    suspend fun authUser(loginRequestSerializer: LoginRequestSerializer): LoginResponseSerializer{
        return Api.retrofitService.authUser(loginRequestSerializer)
    }
}