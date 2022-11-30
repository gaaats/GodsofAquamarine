package com.skgames.traffi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.applinks.AppLinkData
import com.orhanobut.hawk.Hawk
import com.skgames.traffi.databinding.ActivityMainBinding
import com.skgames.traffi.databinding.ActivityNevMainBinding
import com.skgames.traffi.nev.Constance

class NevMainActivity : AppCompatActivity() {

    private lateinit var rtrtrt: ActivityNevMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rtrtrt = ActivityNevMainBinding.inflate(layoutInflater)
        setContentView(rtrtrt.root)

//        hjhjjh(this)
    }

//    fun hjhjjh(context: Context) {
//        AppLinkData.fetchDeferredAppLinkData(
//            context
//        ) { appLinkData: AppLinkData? ->
//            appLinkData?.let {
//                val bgbggbg = appLinkData.targetUri.host.toString()
//                Hawk.put(Constance.KEY_APP_LINK_DATA, bgbggbg)
//            }
//            if (appLinkData == null) {
//            }
//        }
//    }
}