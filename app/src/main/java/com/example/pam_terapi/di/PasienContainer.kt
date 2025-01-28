package com.example.pam_terapi.di

import com.example.pam_terapi.repository.NetworkKontakRepository
import com.example.pam_terapi.repository.PasienRepository
import com.example.pam_terapi.service_api.PasienService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer{
    val kontakRepository: PasienRepository
}

class PasienContainer: AppContainer{
    private val baseUrl= "http://10.0.2.2:3000/api/"
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit=Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl).build()

    private val pasienService: PasienService by lazy {
        retrofit.create(PasienService::class.java)
    }

    override val kontakRepository: PasienRepository by lazy {
        NetworkKontakRepository(pasienService)
    }
}