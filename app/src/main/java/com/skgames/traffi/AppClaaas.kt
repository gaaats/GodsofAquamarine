package com.skgames.traffi

import android.app.Application
import com.onesignal.OneSignal
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClaaas : Application() {
    companion object {
//        var apppaaa = "appsChecker"
//        var ggeeok = "geo"
//        var bbbvv: String? = "mainid"
//        var vovovo: String? = "d11"
//        var kokos: String? = "countryCode"
//        var hkhkhkhk: String? = "c11"
//        var vfvvfvf = "link"

    }



    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        OneSignal.initWithContext(this)
        OneSignal.setAppId("b9ac65a3-cb89-4d51-8732-9d574cd861d4")


//
//        Hawk.init(this).build()


    }


}
