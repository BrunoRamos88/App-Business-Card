package com.br.appbusinesscard2

import android.app.Application
import android.os.StrictMode
import com.br.appbusinesscard2.di.daoModule
import com.br.appbusinesscard2.di.repositoryModule
import com.br.appbusinesscard2.di.useCaseModule
import com.br.appbusinesscard2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(viewModelModule)
            modules(repositoryModule)
            modules(daoModule)
            modules(useCaseModule)
        }

        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
    }
}