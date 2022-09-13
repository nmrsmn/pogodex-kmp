package dev.nmrsmn.pogodex

import android.app.Application
import dev.nmrsmn.pogodex.shared.di.initKoin

class PogodexCollectorApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}
