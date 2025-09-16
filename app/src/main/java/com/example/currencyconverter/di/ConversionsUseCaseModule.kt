package com.example.currencyconverter.di

import com.example.currencyconverter.database.AppDatabase
import com.example.currencyconverter.database.ConversionsDao
import com.example.currencyconverter.datasource.ConversionsDataSource
import com.example.currencyconverter.datasource.ConversionsDataSourceImpl
import com.example.currencyconverter.domain.exchange.usecases.BringConversion
import com.example.currencyconverter.domain.exchange.usecases.ObserveConversionId
import com.example.currencyconverter.domain.exchange.usecases.SaveConversion
import com.example.currencyconverter.remote.RateConverterAPI
import com.example.currencyconverter.repository.ConversionsRepository
import com.example.currencyconverter.repository.ConversionsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConversionsUseCaseModule {

    @Singleton
    @Provides
    fun provideConversionsDao(
        appDatabase: AppDatabase
    ): ConversionsDao = appDatabase.conversionsDao()

    @Singleton
    @Provides
    fun provideConversionsDataSource(
        conversionsDao: ConversionsDao,
        api: RateConverterAPI
    ): ConversionsDataSource = ConversionsDataSourceImpl(
        conversionsDao = conversionsDao,
        api = api
    )

    @Singleton
    @Provides
    fun provideConversionsRepository(
        conversionsDataSource: ConversionsDataSource
    ): ConversionsRepository = ConversionsRepositoryImpl(
        conversionsDataSource = conversionsDataSource
    )

    @Singleton
    @Provides
    fun provideRateConverterAPI(retrofit: Retrofit): RateConverterAPI =
        retrofit.create(RateConverterAPI::class.java)

    @Singleton
    @Provides
    fun provideBringConversion(
        repository: ConversionsRepository
    ): BringConversion = BringConversion(
        repository = repository
    )

    @Singleton
    @Provides
    fun provideSaveConversion(
        repository: ConversionsRepository
    ): SaveConversion = SaveConversion(
        repository = repository
    )

    @Singleton
    @Provides
    fun provideObserveConversionId(
        repository: ConversionsRepository
    ): ObserveConversionId = ObserveConversionId(
        repository = repository
    )
}