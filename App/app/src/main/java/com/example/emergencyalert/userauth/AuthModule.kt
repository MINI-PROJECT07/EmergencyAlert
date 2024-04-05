package com.example.emergencyalert.userauth

import android.app.Application
import com.example.emergencyalert.sensor.AccelerometerSensor
import com.example.emergencyalert.sensor.ParentSensor
import com.example.emergencyalert.userauth.services.AuthService
import com.example.emergencyalert.util.SendSms
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthService(
        app: Application
    ): AuthService {
        return AuthService.create()
    }
}