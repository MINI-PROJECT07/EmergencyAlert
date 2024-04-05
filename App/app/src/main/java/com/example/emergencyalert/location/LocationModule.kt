package com.example.emergencyalert.location

import android.app.Application
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {
    @Provides
    @Singleton
    fun provideDefaultLocationClient(
        app: Application
    ): DefaultLocationClient {
        return DefaultLocationClient(
            app.applicationContext,
            LocationServices.getFusedLocationProviderClient(app.applicationContext)
        )
    }
}