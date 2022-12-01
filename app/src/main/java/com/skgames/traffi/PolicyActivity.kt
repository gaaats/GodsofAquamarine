package com.skgames.traffi

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.*
import android.provider.MediaStore
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.appsflyer.AppsFlyerLib
import com.google.android.material.snackbar.Snackbar
import com.onesignal.OneSignal
import com.skgames.traffi.databinding.ActivityPolicyBinding
import com.skgames.traffi.nev.Constance
import com.skgames.traffi.nev.DataForVebViev
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException


class PolicyActivity : AppCompatActivity() {

    val dataRecived by lazy {
        intent.getParcelableExtra(Constance.KEY_DATA_FOR_VEB_VIEV) as DataForVebViev?
    }

//    private val mainViewModel by viewModels<SortVievModell>()


    private val bnbnbn = 1

    private fun bobik() {
        val lolik = jgidhgjdk.settings
        lolik.javaScriptEnabled = true

        lolik.useWideViewPort = true

        lolik.loadWithOverviewMode = true
        lolik.allowFileAccess = true
        lolik.domStorageEnabled = true
        lolik.userAgentString = lolik.userAgentString.replace("; wv", "")

        lolik.javaScriptCanOpenWindowsAutomatically = true
        lolik.setSupportMultipleWindows(false)

        lolik.displayZoomControls = false
        lolik.builtInZoomControls = true
        lolik.setSupportZoom(true)

        lolik.pluginState = WebSettings.PluginState.ON
        lolik.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        lolik.setAppCacheEnabled(true)

        lolik.allowContentAccess = true
    }

    var bggbggb: ValueCallback<Array<Uri>>? = null
    lateinit var jgidhgjdk: WebView

    var urlfififif = ""
    fun vvovol(lurlurlurlurlur: String?) {
        if (!lurlurlurlurlur!!.contains("t.me")) {

            if (urlfififif == "") {
                urlfififif = getSharedPreferences(
                    "SP_WEBVIEW_PREFS",
                    AppCompatActivity.MODE_PRIVATE
                ).getString(
                    "SAVED_URL",
                    lurlurlurlurlur
                ).toString()

                val cccvfc =
                    getSharedPreferences("SP_WEBVIEW_PREFS", AppCompatActivity.MODE_PRIVATE)
                val ededededededed = cccvfc.edit()
                ededededededed.putString("SAVED_URL", lurlurlurlurlur)
                ededededededed.apply()
            }
        }
    }

    lateinit var dededef: ActivityPolicyBinding
    var kgjfhdkxf: String? = null


    private fun bbnbnko(): String {
        val ftgttg = getSharedPreferences("SP_WEBVIEW_PREFS", AppCompatActivity.MODE_PRIVATE)
        val packfrfrrfr = "com.skgames.traffi"


//        val bnbbj:String? = Hawk.get(hkhkhkhk, "null")
        Log.d("lolo", "dataRecived in vebViev $dataRecived")
        val appsFlyData = dataRecived?.appsFlyerDattaGotten ?: "null"

//        val advId: String? = Hawk.get(bbbvv, "null")
        val advId = dataRecived?.advertisingIdClient ?: "null"

//        val dpOnefrfrr: String? = Hawk.get(vovovo, "null")
        val appLinkData = dataRecived?.appLinkData ?: "null"
//        Snackbar.make(
//            dededef.root, "appLinkData is ${appLinkData}",
//            Snackbar.LENGTH_SHORT
//        ).show()

        lifecycleScope.launch {
//            delay(1500)
//            Snackbar.make(
//                dededef.root, "appsFlyerDattaGotten is ${appsFlyData}",
//                Snackbar.LENGTH_SHORT
//            ).show()
        }

//        val gtgttg = Hawk.get(vfvvfvf, "null")
        val linkkk = dataRecived?.linkViev ?: "null"

        val vcdcdcdcd = AppsFlyerLib.getInstance().getAppsFlyerUID(this)

        AppsFlyerLib.getInstance().setCollectAndroidID(true)

        val bbgbg = "deviceID="
        val frfrfrfr = "sub_id_1="
        val adidddddd = "ad_id="
        val sub4frfrr = "sub_id_4="
        val bgbggbbg = "sub_id_5="
        val sub6frfr = "sub_id_6="


        val nononok = "naming"
        val bnbnbn = "deeporg"

        val eerret = Build.VERSION.RELEASE


        var hylphlyplhly = ""
        if (appsFlyData != "null") {
            hylphlyplhly =
                "$linkkk$frfrfrfr$appsFlyData&$bbgbg$vcdcdcdcd&$adidddddd$advId&$sub4frfrr$packfrfrrfr&$bgbggbbg$eerret&$sub6frfr$nononok"
            pussshi(vcdcdcdcd.toString())
        } else {
            hylphlyplhly =
                "$linkkk$frfrfrfr$appLinkData&$bbgbg$vcdcdcdcd&$adidddddd$advId&$sub4frfrr$packfrfrrfr&$bgbggbbg$eerret&$sub6frfr$bnbnbn"
            pussshi(vcdcdcdcd.toString())
        }
        Snackbar.make(
            dededef.root, "Link is ${hylphlyplhly}",
            Snackbar.LENGTH_SHORT
        ).show()
        Log.d("lolo", "link from vebViev is $hylphlyplhly")
        return ftgttg.getString("SAVED_URL", hylphlyplhly).toString()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Log.d("testVievModel", "vievmodelId $mainViewModel")
        dededef = ActivityPolicyBinding.inflate(layoutInflater)
        setContentView(dededef.root)

        jgidhgjdk = dededef.vvveeev

        //todo return later
//        Snackbar.make(
//            dededef.root, "Loading...",
//            Snackbar.LENGTH_LONG
//        ).show()


        val gttgththy = CookieManager.getInstance()
        gttgththy.setAcceptCookie(true)
        gttgththy.setAcceptThirdPartyCookies(jgidhgjdk, true)
        bobik()
        val ghghgh: Activity = this
        jgidhgjdk.webViewClient = object : WebViewClient() {


            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                try {
                    if (URLUtil.isNetworkUrl(url)) {
                        return false
                    }
                    if (bgbggbffff(url)) {

                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } else {

                        Toast.makeText(
                            applicationContext,
                            "Application is not installed",
                            Toast.LENGTH_LONG
                        ).show()
                        startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=org.telegram.messenger")
                            )
                        )
                    }
                    return true
                } catch (e: Exception) {
                    return false
                }
                view.loadUrl(url)
            }


            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                vvovol(url)
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                Toast.makeText(ghghgh, description, Toast.LENGTH_SHORT).show()
            }


        }
        jgidhgjdk.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView, filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                bggbggb?.onReceiveValue(null)
                bggbggb = filePathCallback
                var totya: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (totya!!.resolveActivity(packageManager) != null) {

                    var bhnhnhnhhn: File? = null
                    try {
                        bhnhnhnhhn = noka()
                        totya.putExtra("PhotoPath", kgjfhdkxf)
                    } catch (ex: IOException) {
                    }

                    if (bhnhnhnhhn != null) {
                        kgjfhdkxf = "file:" + bhnhnhnhhn.absolutePath
                        totya.putExtra(
                            MediaStore.EXTRA_OUTPUT,
                            Uri.fromFile(bhnhnhnhhn)
                        )
                    } else {
                        totya = null
                    }
                }
                val boka = Intent(Intent.ACTION_GET_CONTENT)
                boka.addCategory(Intent.CATEGORY_OPENABLE)
                boka.type = "image/*"
                val gtgtgtt: Array<Intent?> =
                    totya?.let { arrayOf(it) } ?: arrayOfNulls(0)
                val opopopop = Intent(Intent.ACTION_CHOOSER)
                opopopop.putExtra(Intent.EXTRA_INTENT, boka)
                opopopop.putExtra(Intent.EXTRA_TITLE, getString(R.string.take_imagefgtgt))
                opopopop.putExtra(Intent.EXTRA_INITIAL_INTENTS, gtgtgtt)
                startActivityForResult(
                    opopopop, bnbnbn
                )
                return true
            }

            @Throws(IOException::class)
            private fun noka(): File {
                var frfrrfr = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "DirectoryNameHere"
                )
                if (!frfrrfr.exists()) {
                    frfrrfr.mkdirs()
                }

                frfrrfr =
                    File(frfrrfr.toString() + File.separator + "IMG_" + System.currentTimeMillis() + ".jpg")
                return frfrrfr
            }

        }

        jgidhgjdk.loadUrl(bbnbnko())
    }


    private fun pussshi(string: String) {
        OneSignal.setExternalUserId(
            string,
            object : OneSignal.OSExternalUserIdUpdateCompletionHandler {
                override fun onSuccess(results: JSONObject) {
                    try {
                        if (results.has("push") && results.getJSONObject("push").has("success")) {
                            val frrfrfrrf = results.getJSONObject("push").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for push status: $frrfrfrrf"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("email") && results.getJSONObject("email").has("success")) {
                            val vccxddddddddd =
                                results.getJSONObject("email").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for email status: $vccxddddddddd"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    try {
                        if (results.has("sms") && results.getJSONObject("sms").has("success")) {
                            val frfrfr = results.getJSONObject("sms").getBoolean("success")
                            OneSignal.onesignalLog(
                                OneSignal.LOG_LEVEL.VERBOSE,
                                "Set external user id for sms status: $frfrfr"
                            )
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(error: OneSignal.ExternalIdError) {
                    OneSignal.onesignalLog(
                        OneSignal.LOG_LEVEL.VERBOSE,
                        "Set external user id done with error: $error"
                    )
                }
            })
    }


    private fun bgbggbffff(uri: String): Boolean {

        val goolka = packageManager
        try {

            goolka.getPackageInfo("org.telegram.messenger", PackageManager.GET_ACTIVITIES)

            return true
        } catch (e: PackageManager.NameNotFoundException) {

        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode != bnbnbn || bggbggb == null) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        var fnrnfnrf: Array<Uri>? = null

        if (resultCode == AppCompatActivity.RESULT_OK) {
            if (data == null || data.data == null) {
                fnrnfnrf = arrayOf(Uri.parse(kgjfhdkxf))
            } else {
                val vfvddsser = data.dataString
                if (vfvddsser != null) {
                    fnrnfnrf = arrayOf(Uri.parse(vfvddsser))
                }
            }
        }
        bggbggb?.onReceiveValue(fnrnfnrf)
        bggbggb = null
    }


    private var jkikiiik = false
    override fun onBackPressed() {


        if (jgidhgjdk.canGoBack()) {
            if (jkikiiik) {
                jgidhgjdk.stopLoading()
                jgidhgjdk.loadUrl(urlfififif)
            }
            this.jkikiiik = true
            jgidhgjdk.goBack()
            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                jkikiiik = false
            }, 2000)

        } else {
            super.onBackPressed()
        }
    }


}