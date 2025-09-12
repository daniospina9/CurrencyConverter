package com.example.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.example.currencyconverter.database.AppDatabase
import com.example.currencyconverter.database.CurrenciesDao
import com.example.currencyconverter.datasource.CurrenciesDataSource
import com.example.currencyconverter.datasource.CurrenciesDataSourceImpl
import com.example.currencyconverter.remote.CurrenciesAPI
import com.example.currencyconverter.repository.CurrenciesRepository
import com.example.currencyconverter.repository.CurrenciesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "currencies-db"
    ).build()

    @Singleton
    @Provides
    fun provideCurrenciesDao(
        appDatabase: AppDatabase
    ): CurrenciesDao = appDatabase.currenciesDao()

    @Singleton
    @Provides
    fun provideCurrenciesDataSource(
        currenciesDao: CurrenciesDao,
        api: CurrenciesAPI
    ): CurrenciesDataSource = CurrenciesDataSourceImpl(
        currenciesDao = currenciesDao,
        api = api
    )

    @Singleton
    @Provides
    fun provideCurrenciesRepository(
        currenciesDataSource: CurrenciesDataSource
    ): CurrenciesRepository = CurrenciesRepositoryImpl(
        currenciesDataSource = currenciesDataSource
    )
}