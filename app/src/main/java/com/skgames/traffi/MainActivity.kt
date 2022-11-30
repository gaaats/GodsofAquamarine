package com.skgames.traffi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.facebook.applinks.AppLinkData
import com.orhanobut.hawk.Hawk
import com.skgames.traffi.AppClaaas.Companion.kokos
import com.skgames.traffi.AppClaaas.Companion.ggeeok
import com.skgames.traffi.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var rtrtrt: ActivityMainBinding

    fun hjhjjh(context: Context) {
        AppLinkData.fetchDeferredAppLinkData(
            context
        ) { appLinkData: AppLinkData? ->
            appLinkData?.let {
                val bgbggbg = appLinkData.targetUri.host.toString()
                Hawk.put(AppClaaas.vovovo, bgbggbg)
            }
            if (appLinkData == null) {
            }
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        rtrtrt = ActivityMainBinding.inflate(layoutInflater)
        setContentView(rtrtrt.root)

        hjhjjh(this)

        val vbggbbgg = Executors.newSingleThreadScheduledExecutor()
        var ffgtgtt: String? = Hawk.get(kokos, null)
        var voda: String? = Hawk.get(ggeeok, null)
        vbggbbgg.scheduleAtFixedRate({
            if (ffgtgtt != null && voda != null) {
                vbggbbgg.shutdown()
                gttgtth()
            } else {
                ffgtgtt = Hawk.get(kokos)
                voda = Hawk.get(ggeeok)
            }
        }, 0, 1, TimeUnit.SECONDS)

        GlobalScope.launch(Dispatchers.IO) {
            frfrrfrf
        }
    }




    private suspend fun jkjkjkjk() {
        val sssookkl = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://pro.ip-api.com/")
            .build()
            .create(ServiceApi::class.java)
        val nmnmnmkh = sssookkl.getData().body()?.countryCode

        Hawk.put(kokos, nmnmnmkh)
    }

    private val frfrrfrf: Job = GlobalScope.launch(Dispatchers.IO) {
        jkjkjkjk()
        getDataDev()
    }

    private fun gttgtth() {
        val frfrgrt = Intent(this@MainActivity, FiiiiilllttttOneeeActivity::class.java)
        Log.d("lolo", "i am goingo to SortOneActivity")
        startActivity(frfrgrt)
        finish()
    }

    private suspend fun getDataDev() {

        val yhhyyyh = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://godsofaquamarine.xyz/")
            .build()
            .create(ServiceApi::class.java)
        val cdccd = yhhyyyh.getDataDev().body()?.view.toString()
        val momma = yhhyyyh.getDataDev().body()?.appsChecker.toString()
        val cccooks = yhhyyyh.getDataDev().body()?.geo.toString()


        Log.d("lolo", "frfrrfgr $cdccd")
        Log.d("lolo", "appsCheckerfgtt $momma")
        Log.d("lolo", "retroDatafrffr $cccooks")


        Hawk.put(AppClaaas.vfvvfvf, cdccd)
        Hawk.put(AppClaaas.apppaaa, momma)
        Hawk.put(AppClaaas.ggeeok, cccooks)

    }


}