package com.example.emergencyalert.sensor

import android.app.Application
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
}