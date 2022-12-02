package com.skgames.traffi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.google.android.material.snackbar.Snackbar
import com.skgames.traffi.databinding.ActivityNevMainBinding
import com.skgames.traffi.nev.Constance
import com.skgames.traffi.nev.DataFromApiResource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped


@AndroidEntryPoint
@ActivityScoped
class NevMainActivity : AppCompatActivity() {


    private val mainViewModel by viewModels<SortVievModell>()

    private var _binding: ActivityNevMainBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityNevMainBinding = null")

    var lolo = "ffffff"

    private val appsFlyerConversionListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            val dataGotten = data?.get("lolo").toString()
            Snackbar.make(
                binding.root, "naming from ACTIVITY is ${dataGotten}",
                Snackbar.LENGTH_LONG
            ).show()
        }

        override fun onConversionDataFail(p0: String?) {}
        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
        override fun onAttributionFailure(p0: String?) {}
    }


    private fun fetchDeferredAppLinkData(context: Context) {
        Log.d("appLinkDataaaa", "i am in fetchDeferredAppLinkData in NevMainActivity")
        AppLinkData.fetchDeferredAppLinkData(
            context
        ) { appLinkData: AppLinkData? ->

            appLinkData?.let {

                val daaata = appLinkData.targetUri.host.toString()
                mainViewModel.getffFetchDeferredAppLinkData(daaata)

//                saveToSharedPref(Constance.KEY_APP_LINK_DATA, daaata)

//                Snackbar.make(
//                    binding.root, "appLinkData from ACTIVITY is ${daaata}",
//                    Snackbar.LENGTH_SHORT
//                ).show()

                Log.d(
                    "appLinkDataaaa",
                    "data from fetchDeferredAppLinkData in NevMainActivity ${daaata}"
                )

            }
            if (appLinkData == null) {
            }
        }
    }

    private fun saveToSharedPref(key: String, daaata: String) {
        val sharedPreferences =
            getSharedPreferences(Constance.KEY_MAIN_FOR_SHARED_PREF, MODE_PRIVATE)
        val editable = sharedPreferences.edit()
        editable.putString(key, daaata)
        editable.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _binding = ActivityNevMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        mainViewModel.fetchDeferredAppLinkData(this)
        fetchDeferredAppLinkData(this)

        AppsFlyerLib.getInstance()
            .init("EUBo2sR9eDH3fa45JwM2Y7", appsFlyerConversionListener, applicationContext)
        AppsFlyerLib.getInstance().start(this)



//        mainViewModel.ansvFromDevil.observe(this) {
//            when (it) {
//                is DataFromApiResource.Success -> {
////                    mainViewModel.makeCheck()
//
//                    when (mainViewModel.currentMode.value) {
//
//                        SortClass.MODERATION -> {
//                            Log.d(
//                                "lolo",
//                                "mainViewModel.currentMode.value ${mainViewModel.currentMode.value}"
//                            )
//                            Log.d("lolo", "SortClass.MODERATION")
//
//                            val intent = Intent(this, GaammActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        }
//                        SortClass.TEST_VEB -> {
//                            val dataForSend = mainViewModel.sendDataForVebVeiv()
//                            Log.d("lolo", "SortClass.TEST_VEB")
//                            val intent = Intent(this, PolicyActivity::class.java).also {
//                                it.putExtra(Constance.KEY_DATA_FOR_VEB_VIEV, dataForSend)
//                            }
//                            startActivity(intent)
//                            finish()
//                        }
//                        SortClass.TEST_ZAGLUSHKA_LIKE_MODERATION -> {
//                            Log.d("lolo", "SortClass.TEST_ZAGLUSHKA_LIKE_MODERATION")
//                            val intent = Intent(this, GaammActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        }
//                        SortClass.REAL_START -> {
//                            val dataForSend = mainViewModel.sendDataForVebVeiv()
//                            Log.d("lolo", "SortClass.REAL_START")
//                            Log.d("lolo", "dataForSend $dataForSend")
//                            val intent = Intent(this, PolicyActivity::class.java).also {
//                                it.putExtra(Constance.KEY_DATA_FOR_VEB_VIEV, dataForSend)
//                            }
//                            startActivity(intent)
//                            finish()
//                        }
//                        else -> {
//                            Log.d("lolo", "SortClass. else")
//                            val intent = Intent(this, GaammActivity::class.java)
//                            startActivity(intent)
//                            finish()
//                        }
//                    }
//
//                }
//                is DataFromApiResource.Loading -> {
//                    //do nothing
//                }
//                is DataFromApiResource.Error -> {
//                    //make for some error
//                    Snackbar.make(
//                        binding.root,
//                        "There is some error, close APP and try again",
//                        Snackbar.LENGTH_LONG
//                    ).show()
//                }
//            }
//        }


        mainViewModel.currentMode.observe(this) {
            when (it) {
                SortClass.LOADING -> {

                }
                SortClass.MODERATION -> {
                    Log.d(
                        "lolo",
                        "mainViewModel.currentMode.value ${mainViewModel.currentMode.value}"
                    )
                    Log.d("lolo", "SortClass.MODERATION")

                    val intent = Intent(this, GaammActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                SortClass.TEST_VEB -> {
                    val dataForSend = mainViewModel.sendDataForVebVeiv()
                    Log.d("lolo", "SortClass.TEST_VEB")
                    val intent = Intent(this, PolicyActivity::class.java).also {
                        it.putExtra(Constance.KEY_DATA_FOR_VEB_VIEV, dataForSend)
                    }
                    startActivity(intent)
                    finish()
                }
                SortClass.TEST_ZAGLUSHKA_LIKE_MODERATION -> {
                    Log.d("lolo", "SortClass.TEST_ZAGLUSHKA_LIKE_MODERATION")
                    val intent = Intent(this, GaammActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                SortClass.REAL_START -> {
                    val dataForSend = mainViewModel.sendDataForVebVeiv()
                    Log.d("lolo", "SortClass.REAL_START")
                    Log.d("lolo", "dataForSend $dataForSend")
                    val intent = Intent(this, PolicyActivity::class.java).also {
                        it.putExtra(Constance.KEY_DATA_FOR_VEB_VIEV, dataForSend)
                    }
                    startActivity(intent)
                    finish()
                }
                else -> {
                    Log.d("lolo", "SortClass. else")
                    val intent = Intent(this, GaammActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}