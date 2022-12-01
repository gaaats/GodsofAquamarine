package com.skgames.traffi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.skgames.traffi.databinding.ActivityNevMainBinding
import com.skgames.traffi.nev.Constance
import com.skgames.traffi.nev.DataFromApiResource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped


@AndroidEntryPoint
@ActivityScoped
class NevMainActivity : AppCompatActivity() {


    private val mainViewModel by viewModels<SortVievModell>()

    private var _binding: ActivityNevMainBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityNevMainBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        Log.d("testVievModel", "vievmodelId $mainViewModel")
        _binding = ActivityNevMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.ansvFromDevil.observe(this) {
            when (it) {
                is DataFromApiResource.Success -> {
                    mainViewModel.makeCheck()

                    when (mainViewModel.currentMode.value) {

                        SortClass.MODERATION -> {
                            Log.d("lolo", "mainViewModel.currentMode.value ${mainViewModel.currentMode.value}")
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
                is DataFromApiResource.Loading -> {
                    //do nothing
                }
                is DataFromApiResource.Error -> {
                    //make for some error
                    Snackbar.make(
                        binding.root,
                        "There is some error, close APP and try again",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

//        mainViewModel.currentMode.observe(this) {
//            when (it) {
//                SortClass.MODERATION -> {
//                    val intent = Intent(this, GaammActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                SortClass.TEST_VEB -> {
//                    val intent = Intent(this, PolicyActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                SortClass.TEST_ZAGLUSHKA_LIKE_MODERATION -> {
//                    val intent = Intent(this, GaammActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//                SortClass.REAL_START -> {
//                    val intent = Intent(this, PolicyActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
//
//            }
//        }
    }
}