package ru.nsu.malov.lab9.network.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import ru.nsu.malov.lab9.network.serializers.GetBalanceResponseSerializer
import ru.nsu.malov.lab9.network.serializers.LoginRequestSerializer
import ru.nsu.malov.lab9.network.serializers.LoginResponseSerializer
import ru.nsu.malov.lab9.network.serializers.SendMoneyRequestSerializer
import ru.nsu.malov.lab9.network.serializers.SendMoneyResponseSerializer

// todo: поменять урл
private const val BASE_URL = "http://192.168.31.98:8080"

private val retrofitClient = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface ApiService{
    @POST("/login")
    suspend fun authUser(@Body loginRequestSerializer: LoginRequestSerializer): LoginResponseSerializer

    @GET("/balance/{token}")
    suspend fun getBalance(@Path("token") token: String): GetBalanceResponseSerializer

    @PATCH("/sendMoney/{token}")
    suspend fun getMoney(@Path("token") token: String, sendMoneyRequestSerializer: SendMoneyRequestSerializer): SendMoneyResponseSerializer
}

object Api {
    val retrofitService : ApiService by lazy {
        retrofitClient.create(ApiService::class.java)
    }
}