package com.skgames.traffi

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.skgames.traffi.databinding.FragmentEnnnntterBinding
import androidx.fragment.app.Fragment
import android.view.LayoutInflater


class EnnnntterFragment : Fragment() {


    private fun gthyhuyjuju() {
        Snackbar.make(
            ssskkaa.root,
            "Some error...",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private var soska: FragmentEnnnntterBinding? = null
    private val ssskkaa get() = soska ?: throw RuntimeException("FragmentEnnnntterBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        soska = FragmentEnnnntterBinding.inflate(inflater, container, false)
        return ssskkaa.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {

            ssskkaa.btnPlayGameeee.setOnClickListener {
                findNavController().navigate(R.id.action_ennnntterFragment_to_gaaaamiFragment)
            }

        } catch (e: Exception) {
            gthyhuyjuju()
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        soska = null
        super.onDestroy()
    }

}