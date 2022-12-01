package com.skgames.traffi

import android.app.Application
import com.onesignal.OneSignal
import com.skgames.traffi.nev.Constance
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClaaas : Application() {
    companion object {
//        const val koko = "b9ac65a3-cb89-4d51-8732-9d574cd861d4"
        var apppaaa = "appsChecker"
        var ggeeok = "geo"
        var bbbvv: String? = "mainid"
        var vovovo: String? = "d11"
        var kokos: String? = "countryCode"
        var hkhkhkhk: String? = "c11"
        var vfvvfvf = "link"

    }



    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        OneSignal.initWithContext(this)
        OneSignal.setAppId(Constance.ONE_SIGNAL_ID)


//
//        Hawk.init(this).build()


    }


}
