package com.skgames.traffi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.orhanobut.hawk.Hawk
import com.skgames.traffi.AppClaaas.Companion.hkhkhkhk
import com.skgames.traffi.AppClaaas.Companion.vovovo
import com.skgames.traffi.AppClaaas.Companion.apppaaa
import com.skgames.traffi.AppClaaas.Companion.kokos
import com.skgames.traffi.AppClaaas.Companion.ggeeok
import com.skgames.traffi.databinding.ActivityFiiilllttTtvvvoBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class FiiilllttTTvvvoActivity : AppCompatActivity() {
    lateinit var bindAsffrffrr: ActivityFiiilllttTtvvvoBinding

    private fun intLONEfrfrrf() {
        Log.d("lolo", "go to GaaaammmyyyActivity from SortTvoActivity")
        val gtggtgt = Intent(this@FiiilllttTTvvvoActivity, GaammActivity::class.java)
        Hawk.put(ggeeok, null)
        Hawk.put(kokos, null)
        Hawk.put(apppaaa, null)
        startActivity(gtggtgt)
        finish()
    }



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        bindAsffrffrr = ActivityFiiilllttTtvvvoBinding.inflate(layoutInflater)
        setContentView(bindAsffrffrr.root)

        val vbvvv: String? = Hawk.get(apppaaa, "null")

        if (vbvvv == "1") {
            AppsFlyerLib.getInstance()
                .init("EUBo2sR9eDH3fa45JwM2Y7", bkbkbk, applicationContext)
            AppsFlyerLib.getInstance().start(this)
        }

        hyhyhhy()

    }

    private val bkbkbk = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            val dataGotten = data?.get("campaign").toString()
            Hawk.put(hkhkhkhk, dataGotten)
        }

        override fun onConversionDataFail(p0: String?) {}
        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
        override fun onAttributionFailure(p0: String?) {}
    }


    private fun hyhyhhy() {

        val ftgtgttg: String = Hawk.get(ggeeok)
        val domka: String = Hawk.get(kokos, "null")

        val dadadadda: String? = Hawk.get(vovovo, "null")
        val apapapap: String? = Hawk.get(apppaaa, "null")
        var anananan: String? = Hawk.get(hkhkhkhk)

        if (apapapap == "1") {

            Log.d("lolo", "appsChfrfgtg $apapapap")

            val bnbnbnb = Executors.newSingleThreadScheduledExecutor()
            bnbnbnb.scheduleAtFixedRate({
                if (anananan != null) {
                    Log.d("lolo", "namingfrfrrr $anananan")
                    Log.d("lolo", "deeplinkgtgttg $dadadadda")

                    if (anananan!!.contains("tdb2") || ftgtgttg.contains(
                            domka
                        ) || dadadadda!!.contains("tdb2")
                    ) {
                        bnbnbnb.shutdown()
                        yuyuy()
                        Log.d("lolo", "namingfrfrrr $anananan")
                        Log.d("lolo", "deeplinkgtgttg $dadadadda")
                    } else {
                        bnbnbnb.shutdown()
                        intLONEfrfrrf()

                        Log.d("lolo", "namingfrfrrr $anananan")
                        Log.d("lolo", "deeplinkgtgttg $dadadadda")
                    }
                } else {
                    anananan = Hawk.get(hkhkhkhk)

                    Log.d("lolo", "namingfrfrrr $anananan")
                    Log.d("lolo", "deeplinkgtgttg $dadadadda")
                }
            }, 0, 1, TimeUnit.SECONDS)
        } else if (ftgtgttg.contains(domka)) {

            Log.d("lolo", "namingfrfrrr $anananan")
            Log.d("lolo", "deeplinkgtgttg $dadadadda")
            yuyuy()
        } else {

            Log.d("lolo", "namingfrfrrr $anananan")
            Log.d("lolo", "deeplinkgtgttg $dadadadda")
            intLONEfrfrrf()
        }

    }

    private fun yuyuy() {
        Log.d("lolo", "go to BrovserActivity from SortTvoActivity")
        val nnpnpn = Intent(this@FiiilllttTTvvvoActivity, PolicyActivity::class.java)
        Hawk.put(ggeeok, null)
        Hawk.put(kokos, null)
        Hawk.put(apppaaa, null)
        startActivity(nnpnpn)
        finish()
    }


}