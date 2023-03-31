package com.dentons.raidapp

import android.app.Application
import com.dentons.raidapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RaidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@RaidApp)
            modules(appModules)
        }
    }
}