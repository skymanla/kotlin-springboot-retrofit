package com.newsol.ai_plus.config

import com.newsol.ai_plus.dto.OpenApiInterface
import okhttp3.OkHttpClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Configuration
class RetrofitConfig {
    @Bean("okHttpClient")
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder().apply {
                connectTimeout(10, TimeUnit.SECONDS)
                writeTimeout(10, TimeUnit.SECONDS)
                readTimeout(10, TimeUnit.SECONDS)
            }.build()
    }

    @Bean("retrofitClient")
    fun retrofitClient(@Qualifier("okHttpClient") client: OkHttpClient): Retrofit {
        val baseUrl: String = "https://jsonplaceholder.typicode.com/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Bean("restService")
    fun restService(@Qualifier("retrofitClient") retrofit: Retrofit): OpenApiInterface {
        return retrofit.create(OpenApiInterface::class.java)
    }
}