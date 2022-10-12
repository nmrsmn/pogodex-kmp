package dev.nmrsmn.pogodex

import android.app.Application
import dev.nmrsmn.pogodex.shared.di.initKoin
import org.koin.android.ext.koin.androidContext

class PogodexCollectorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin(appDeclaration = {
            androidContext(applicationContext)
        })
    }
}
