package com.skgames.traffi

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {

    @GET("json/?key=KXgMIA7HSEcn0SV")
    suspend fun getData(): Response<CountryCodeJS>

    @GET("const.json")
    suspend fun getDataDev(): Response<GeoDev>
}

@Keep
data class CountryCodeJS(
    @SerializedName("city")
    val ghgggh: String,
    @SerializedName("country")
    val hyhhy: String,
    @SerializedName("countryCode")
    val cvcvcv: String,
)

@Keep
data class GeoDev(
    @SerializedName("geo")
    val hyhhyy: String,
    @SerializedName("view")
    val gyhyhy: String,
    @SerializedName("appsChecker")
    val cvcvcv: String,
)