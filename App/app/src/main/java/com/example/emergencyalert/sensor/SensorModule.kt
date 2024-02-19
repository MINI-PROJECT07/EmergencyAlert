package com.example.emergencyalert.sensor

import android.app.Application
import com.example.emergencyalert.util.SendSms
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {
    @Provides
    @Singleton
    fun provideAccelerometerSensor(
        app: Application
    ): ParentSensor {
        return AccelerometerSensor(
            context = app
        )
    }
    @Provides
    @Singleton
    fun provideSmsService(
        app: Application
    ): SendSms {
        return SendSms(
            context = app
        )
    }

}