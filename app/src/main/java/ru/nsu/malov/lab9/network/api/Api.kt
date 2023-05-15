package ru.nsu.malov.lab9.network.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import ru.nsu.malov.lab9.network.serializers.LoginRequestSerializer
import ru.nsu.malov.lab9.network.serializers.LoginResponseSerializer

// todo: поменять урл
private const val BASE_URL = "http://localhost:8080"

private val retrofitClient = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface ApiService{
    @POST("/login")
    suspend fun authUser(@Body loginRequestSerializer: LoginRequestSerializer): LoginResponseSerializer
}

object Api {
    val retrofitService : ApiService by lazy {
        retrofitClient.create(ApiService::class.java)
    }
}