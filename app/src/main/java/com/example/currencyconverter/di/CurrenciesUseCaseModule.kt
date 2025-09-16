package com.example.currencyconverter.di

import com.example.currencyconverter.domain.exchange.usecases.FetchCurrenciesList
import com.example.currencyconverter.domain.exchange.usecases.ObserveAllCurrencies
import com.example.currencyconverter.domain.exchange.usecases.RefreshCurrenciesList
import com.example.currencyconverter.domain.exchange.usecases.ReplaceAllCurrencies
import com.example.currencyconverter.remote.CurrenciesAPI
import com.example.currencyconverter.repository.CurrenciesRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrenciesUseCaseModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://moneymorph.dev/api/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Singleton
    @Provides
    fun provideCurrenciesAPI(retrofit: Retrofit): CurrenciesAPI =
        retrofit.create(CurrenciesAPI::class.java)

    @Singleton
    @Provides
    fun provideFetchCurrenciesList(
        repository: CurrenciesRepository
    ): FetchCurrenciesList = FetchCurrenciesList(
        repository = repository
    )

    @Singleton
    @Provides
    fun provideSaveAllCurrencies(
        repository: CurrenciesRepository
    ): ReplaceAllCurrencies = ReplaceAllCurrencies(
        repository = repository
    )

    @Singleton
    @Provides
    fun provideObserveAllCurrencies(
        repository: CurrenciesRepository
    ): ObserveAllCurrencies = ObserveAllCurrencies(
        repository = repository
    )

    @Singleton
    @Provides
    fun provideRefreshCurrenciesList(
        fetchCurrenciesList: FetchCurrenciesList,
        replaceAllCurrencies: ReplaceAllCurrencies
    ): RefreshCurrenciesList = RefreshCurrenciesList(
        fetchCurrenciesList = fetchCurrenciesList,
        replaceAllCurrencies = replaceAllCurrencies
    )
}