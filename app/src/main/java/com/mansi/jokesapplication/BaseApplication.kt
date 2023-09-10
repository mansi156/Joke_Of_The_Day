package com.mansi.jokesapplication

import android.app.Application
import androidx.multidex.MultiDex
import com.mansi.jokesapplication.di.AppModule
import com.mansi.jokesapplication.di.NetworkModule
import com.mansi.jokesapplication.di.RoomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(AppModule, NetworkModule, RoomModule))
        }
    }
}