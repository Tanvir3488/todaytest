package com.tanvir3488.finaltest.core.Di

import com.tanvir3488.finaltest.core.Utils.CustomInterceptor
import com.tanvir3488.finaltest.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okio.Timeout
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/******

 **** Created By  TANVIR3488 AT 19/8/25 7:52 PM

 ******/

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttp(): OkHttpClient{
        val client = OkHttpClient().newBuilder()
        client.writeTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.addInterceptor(CustomInterceptor())

        return client.build()
    }

    @Provides
    fun provideRetrofit( client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }

}
