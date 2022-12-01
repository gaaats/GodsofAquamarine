package com.skgames.traffi

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.skgames.traffi.databinding.FragmentGaaaamiBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GaaaamiFragment : Fragment() {
    private var vfvvf: FragmentGaaaamiBinding? = null
    private val bgbhhbhb
        get() = vfvvf ?: throw RuntimeException("FragmentGaaaamiBinding = null")

    private fun nhnhnnnh() {
        Snackbar.make(
            bgbhhbhb.root,
            "There is some error, try again",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    val nhjhjjj by lazy {
        listOf(
            ContextCompat.getDrawable(requireActivity(), R.drawable.t1),
            ContextCompat.getDrawable(requireActivity(), R.drawable.t4),
            ContextCompat.getDrawable(requireActivity(), R.drawable.t3),
        )
    }

    val variant1fr = listOf(0, 2, 3)
    val variant2ggt = listOf(2, 3, 5)
    val variant3gtgt = listOf(1, 4, 5)
    val variant4gt = listOf(0, 4, 5)
    val variant5gtgt = listOf(0, 3, 5)


    private fun createAnimatorSetFront(): AnimatorSet {
        return AnimatorInflater.loadAnimator(
            requireContext().applicationContext,
            R.animator.front_animator
        ) as AnimatorSet
    }

    private fun createAnimatorSetBack(): AnimatorSet {
        return AnimatorInflater.loadAnimator(
            requireContext().applicationContext,
            R.animator.back_animator
        ) as AnimatorSet
    }

    private val bgbgvvfv by lazy {
        createAnimatorSetFront()
    }
    private val vfvfvffv by lazy {
        createAnimatorSetFront()
    }
    private val nhnhnnhnh by lazy {
        createAnimatorSetFront()
    }
    private val hhhhhhhhhhh by lazy {
        createAnimatorSetFront()
    }
    private val hyhujujiki by lazy {
        createAnimatorSetFront()
    }
    private val kkkkikii by lazy {
        createAnimatorSetFront()
    }


    private val xcvvvv by lazy {
        createAnimatorSetBack()
    }

    private val nhbbbbggb by lazy {
        createAnimatorSetBack()
    }

    private val gbgnhnhnh by lazy {
        createAnimatorSetBack()
    }
    private val nhjujjuujuj by lazy {
        createAnimatorSetBack()
    }
    private val mmklkk by lazy {
        createAnimatorSetBack()
    }
    private val vfgjjukilko by lazy {
        createAnimatorSetBack()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            bgbhhbhb.root.background.alpha = 195
            val bhnhnhnnh = toto.random()

            for (element in 0..5) {
                if (bhnhnhnnh.contains(element)) {
                    dodik[element].contentDescription = "ok"
                    val imaaaage = ContextCompat.getDrawable(requireActivity(), R.drawable.t5)
                    dodik[element].setImageDrawable(imaaaage)
                } else {
                    dodik[element].contentDescription = "false"
                    dodik[element].setImageDrawable(nhjhjjj.random())
                }
            }


            if (moska) {
                lifecycleScope.launch {
                    manka()
                    bgbgbgffff()
                    vffffggg()
                    sdefdfvfvf()
                    hyujkooppp()
                    vbgmjkloo()

                    delay(2000)

                    mashaa()
                    pasha()
                    xsdvghgh()
                    vfrrthy()
                    bghjj()
                    lanka()
                }


            }


            val bbgbgbgbg = requireContext().applicationContext.resources.displayMetrics.density
            bgbhhbhb.imgElem1.cameraDistance = 8000 * bbgbgbgbg
            bgbhhbhb.imgElem1Back.cameraDistance = 8000 * bbgbgbgbg


            bgbhhbhb.btnImgExitttt.setOnClickListener {
                requireActivity().onBackPressed()
            }

            bgbhhbhb.imgElem1.setOnClickListener {
                manka()
                lifecycleScope.launch {
                    if (bgbhhbhb.imgElem1Back.contentDescription == "ok") {
                        bgbhhbhb.imgElem1.isEnabled = false
                        delay(900)
                        bgbhhbhb.imgElem1.visibility = View.INVISIBLE
                        mashaa()
                        bgbhhbhb.imgElem1Back.contentDescription = "done"
                        if (romka()) vfccccvb()
                    } else {
                        delay(2000)
                        mashaa()
                    }
                }
            }
            bgbhhbhb.imgElem2.setOnClickListener {
                bgbgbgffff()
                lifecycleScope.launch {
                    if (bgbhhbhb.imgElem2Back.contentDescription == "ok") {
                        bgbhhbhb.imgElem2.isEnabled = false
                        delay(900)
                        bgbhhbhb.imgElem2.visibility = View.INVISIBLE
                        pasha()
                        bgbhhbhb.imgElem2Back.contentDescription = "done"
                        if (romka()) vfccccvb()

                    } else {
                        delay(2000)
                        pasha()
                    }
                }

            }

            bgbhhbhb.imgElem3.setOnClickListener {
                vffffggg()
                lifecycleScope.launch {
                    if (bgbhhbhb.imgElem3Back.contentDescription == "ok") {
                        bgbhhbhb.imgElem3.isEnabled = false
                        delay(900)
                        bgbhhbhb.imgElem3.visibility = View.INVISIBLE
                        xsdvghgh()
                        bgbhhbhb.imgElem3Back.contentDescription = "done"

                        if (romka()) vfccccvb()
                    } else {
                        delay(2000)

                        xsdvghgh()
                    }
                }

            }

            bgbhhbhb.imgElem4.setOnClickListener {
                sdefdfvfvf()
                lifecycleScope.launch {
                    if (bgbhhbhb.imgElem4Back.contentDescription == "ok") {
                        bgbhhbhb.imgElem4.isEnabled = false
                        delay(900)
                        bgbhhbhb.imgElem4.visibility = View.INVISIBLE
                        vfrrthy()
                        bgbhhbhb.imgElem4Back.contentDescription = "done"

                        if (romka()) vfccccvb()
                    } else {
                        delay(2000)

                        vfrrthy()
                    }
                }

            }

            bgbhhbhb.imgElem5.setOnClickListener {
                hyujkooppp()
                lifecycleScope.launch {
                    if (bgbhhbhb.imgElem5Back.contentDescription == "ok") {
                        bgbhhbhb.imgElem5.isEnabled = false
                        delay(900)
                        bgbhhbhb.imgElem5.visibility = View.INVISIBLE
                        bghjj()
                        bgbhhbhb.imgElem5Back.contentDescription = "done"

                        if (romka()) vfccccvb()
                    } else {
                        delay(2000)

                        bghjj()
                    }
                }

            }

            bgbhhbhb.imgElem6.setOnClickListener {
                vbgmjkloo()
                lifecycleScope.launch {
                    if (bgbhhbhb.imgElem6Back.contentDescription == "ok") {
                        bgbhhbhb.imgElem6.isEnabled = false
                        delay(900)
                        bgbhhbhb.imgElem6.visibility = View.INVISIBLE
                        lanka()
                        bgbhhbhb.imgElem6Back.contentDescription = "done"

                        if (romka()) vfccccvb()
                    } else {
                        delay(2000)
                        lanka()
                    }
                }
            }


        } catch (e: Exception) {
            nhnhnnnh()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun iniOnClickListener(view: View, funIsFront: Unit, funForBack: Unit) {
        val vfvfvfvfvf = view as ImageView
        funIsFront
        lifecycleScope.launch {
            if (vfvfvfvfvf.contentDescription == "ok") {
                view.isEnabled = false
                delay(1000)
                view.visibility = View.INVISIBLE
                funForBack
            } else {
                funForBack
            }
        }
        if (romka()) {
            vfccccvb()

        }
    }

    private fun vfccccvb() {
        findNavController().navigate(R.id.action_gaaaamiFragment_to_restartFragment)
    }

    private fun gythhyhy(elemFront: ImageView) = elemFront.visibility == View.INVISIBLE

    private fun romka(): Boolean {
        var counttteeer = 0
        for (i in dodik) {
            if (i.contentDescription == "done") {
                counttteeer++
            }
        }
        return counttteeer == 3

    }

    override fun onPause() {
        onDestroy()
        super.onPause()
    }

    private fun mashaa() {
        fanka(
            bgbgvvfv,
            xcvvvv,
            bgbhhbhb.imgElem1,
            bgbhhbhb.imgElem1Back
        )
    }

    private fun pasha() {
        fanka(
            vfvfvffv,
            nhbbbbggb,
            bgbhhbhb.imgElem2,
            bgbhhbhb.imgElem2Back
        )
    }

    private fun vfrrthy() {
        fanka(
            hhhhhhhhhhh,
            nhjujjuujuj,
            bgbhhbhb.imgElem4,
            bgbhhbhb.imgElem4Back
        )
    }

    private fun bghjj() {
        fanka(
            hyhujujiki,
            mmklkk,
            bgbhhbhb.imgElem5,
            bgbhhbhb.imgElem5Back
        )
    }

    private fun xsdvghgh() {
        fanka(
            nhnhnnhnh,
            gbgnhnhnh,
            bgbhhbhb.imgElem3,
            bgbhhbhb.imgElem3Back
        )
    }


    private fun lanka() {
        fanka(
            kkkkikii,
            vfgjjukilko,
            bgbhhbhb.imgElem6,
            bgbhhbhb.imgElem6Back
        )
    }


    private fun manka() {
        bvbvbbvvbvbvb(
            bgbgvvfv, xcvvvv, bgbhhbhb.imgElem1, bgbhhbhb.imgElem1Back
        )
    }

    private fun bgbgbgffff() {
        bvbvbbvvbvbvb(
            vfvfvffv, nhbbbbggb, bgbhhbhb.imgElem2, bgbhhbhb.imgElem2Back
        )
    }

    private fun vffffggg() {
        bvbvbbvvbvbvb(
            nhnhnnhnh, gbgnhnhnh, bgbhhbhb.imgElem3, bgbhhbhb.imgElem3Back
        )
    }

    private fun sdefdfvfvf() {
        bvbvbbvvbvbvb(
            hhhhhhhhhhh, nhjujjuujuj, bgbhhbhb.imgElem4, bgbhhbhb.imgElem4Back
        )
    }

    private fun hyujkooppp() {
        bvbvbbvvbvbvb(
            hyhujujiki, mmklkk, bgbhhbhb.imgElem5, bgbhhbhb.imgElem5Back
        )
    }

    private fun vbgmjkloo() {
        bvbvbbvvbvbvb(
            kkkkikii, vfgjjukilko, bgbhhbhb.imgElem6, bgbhhbhb.imgElem6Back
        )
    }

    private fun fanka(
        frontAnim: AnimatorSet,
        backAnim: AnimatorSet,
        elemFront: ImageView,
        elemBack: ImageView
    ) {
        frontAnim.setTarget(elemBack)
        backAnim.setTarget(elemFront)
        frontAnim.start()
        backAnim.start()
    }


    private fun bvbvbbvvbvbvb(
        frontAnim: AnimatorSet,
        backAnim: AnimatorSet,
        elemFront: ImageView,
        elemBack: ImageView
    ) {
        frontAnim.setTarget(elemFront)
        backAnim.setTarget(elemBack)
        frontAnim.start()
        backAnim.start()
    }


    override fun onDestroy() {
        vfvvf = null
        super.onDestroy()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        vfvvf = FragmentGaaaamiBinding.inflate(inflater, container, false)
        return bgbhhbhb.root
    }

    val toto = listOf(
        variant1fr,
        variant2ggt,
        variant3gtgt,
        variant4gt,
        variant5gtgt,
    ).shuffled()

    var hyhyhy = true
    var moska = true

    val dodik by lazy {
        listOf(
            bgbhhbhb.imgElem1Back,
            bgbhhbhb.imgElem2Back,
            bgbhhbhb.imgElem3Back,
            bgbhhbhb.imgElem4Back,
            bgbhhbhb.imgElem5Back,
            bgbhhbhb.imgElem6Back
        )
    }


}