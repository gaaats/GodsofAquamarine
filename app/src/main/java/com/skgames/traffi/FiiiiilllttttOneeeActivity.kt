package com.skgames.traffi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.skgames.traffi.AppClaaas.Companion.apppaaa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.orhanobut.hawk.Hawk
import com.skgames.traffi.AppClaaas.Companion.bbbvv


class FiiiiilllttttOneeeActivity : AppCompatActivity() {   override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fiiiiillltttt_oneee)
    checkCountry()
}

    private fun intALonehyhyy() {
        val ddede = Intent(this@FiiiiilllttttOneeeActivity, GaammActivity::class.java)
        Hawk.put(AppClaaas.ggeeok, null)
        Hawk.put(AppClaaas.kokos, null)
        Hawk.put(AppClaaas.apppaaa, null)
        startActivity(ddede)
        finish()
    }

    private fun ftgthy(): String? {
        val restCheckftgt: String? = Hawk.get(apppaaa)
        return restCheckftgt
    }

    private fun checkCountry() {

        val isssko = ftgthy()
        Log.d("lolo", "check $isssko")

        if (isssko == "0") {
            intALonehyhyy()

        } else {
            GlobalScope.launch(Dispatchers.Default) {
                fgfgff()
            }
            jiji()
        }
    }

    private fun jiji() {
        Log.d("lolo", "go to SortTvoActivity")
        val tototot = Intent(this@FiiiiilllttttOneeeActivity, FiiilllttTTvvvoActivity::class.java)
        startActivity(tototot)
        finish()
    }

    private fun fgfgff() {
        val gtgttadInfo = AdvertisingIdClient(applicationContext)
        gtgttadInfo.start()
        val bobobo = gtgttadInfo.info.id
        Log.d("lolo", "AdvertisingIdClient $bobobo")
        Hawk.put(bbbvv, bobobo)
    }



}