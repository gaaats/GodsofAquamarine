package com.skgames.traffi

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.skgames.traffi.databinding.FragmentRestartBinding
import kotlinx.coroutines.delay
import kotlin.random.Random
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup



class RestartFragment : Fragment() {
    private var hyhhy: FragmentRestartBinding? = null
    private val hyhyjiki get() = hyhhy ?: throw RuntimeException("FragmentRestartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        hyhhy = FragmentRestartBinding.inflate(inflater, container, false)
        return hyhyjiki.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            val currentPoint = Random.nextInt(from = 100, until = 5000)
            Snackbar.make(
                hyhyjiki.root,
                "You recived $currentPoint points",
                Snackbar.LENGTH_LONG
            ).show()


            lifecycleScope.launchWhenCreated {
                delay(1900)
                findNavController().navigate(R.id.action_restartFragment_to_ennnntterFragment)
            }


        } catch (e: Exception) {
            vfvvf()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onPause() {
        onDestroy()
        super.onPause()
    }

    override fun onDestroy() {
        hyhhy = null
        super.onDestroy()
    }

    private fun vfvvf() {
        Snackbar.make(
            hyhyjiki.root,
            "Some error...",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

}