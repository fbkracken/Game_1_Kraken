package com.ja_company.gameja.ui.main.game


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import android.view.Gravity.*
import android.view.View
import android.widget.FrameLayout
import com.daimajia.androidanimations.library.YoYo
import com.ja_company.gameja.ui.Dialog
import naet.vstserq.game_1_kraken.ui.DialogBuilder
import com.ja_company.gameja.utils.RotationScene
import naet.vstserq.game_1_kraken.views.CapGameView
import com.transitionseverywhere.ArcMotion
import com.transitionseverywhere.ChangeBounds
import com.transitionseverywhere.TransitionManager
import com.transitionseverywhere.TransitionSet
import kotlinx.android.synthetic.main.game_fragment_layout.*
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.ui.base.BaseFragment
import naet.vstserq.game_1_kraken.ui.main.game.GameFragmentViewModel
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.runOnUiThread
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.concurrent.thread


class GameFragment : BaseFragment() {

    val viewModel: GameFragmentViewModel by viewModel()

    override var fragmentTag = "GameFragment"

    override var layoutResId = R.layout.game_fragment_layout

    private val winDialog: Dialog by lazy {
        DialogBuilder.newBuilder()
            .buildCongratulationDialog(context!!, "hello")
            .yellowClick({})
            .greenClick({})
    }

    companion object {
        fun newInstance(): GameFragment {
            return GameFragment().apply { arguments = Bundle().apply {} }
        }
    }

    var animationCount = 0
    var animationCount1 = 1
    var animationCount2 = 4
    var level = 1

    @SuppressLint("RtlHardcoded")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (viewModel.getCoinIntoCap()) {
            1 -> btn_view.init(level).showCoinsAndHide()
            2 -> btn_2_view.init(level).showCoinsAndHide()
            else -> btn_3_view.init(level).showCoinsAndHide()
        }

        play_btn.onClick {

            YoYo.with(RotationScene(scene_view.rotation, (scene_view.rotation + listOf(-180F, -360F, -720F).random())))
                .duration(5000)
                .playOn(scene_view)

            play_btn.isClickable = false
            btn_view.isClickable = false
            btn_2_view.isClickable = false
            btn_3_view.isClickable = false

            thread {
                (0 until 100).map {
                    SystemClock.sleep(50)
                    runOnUiThread {
                        YoYo.with(RotationScene(btn_view.rotation, (scene_view.rotation * -1)))
                            .duration(50)
                            .playOn(btn_view)

                        YoYo.with(RotationScene(btn_2_view.rotation, (scene_view.rotation * -1)))
                            .duration(50)
                            .playOn(btn_2_view)

                        YoYo.with(RotationScene(btn_3_view.rotation, (scene_view.rotation * -1)))
                            .duration(50)
                            .playOn(btn_3_view)
                    }
                }
            }

            thread {
                val animCount = listOf(5, 10, 15, 20, 25).random()
                (0 until animCount).map {
                    runOnUiThread {
                        if ((it + 1) == animCount) {
                            play_btn.isClickable = true
                            btn_view.isClickable = true
                            btn_2_view.isClickable = true
                            btn_3_view.isClickable = true
                            level++
                        }

                        animateView(
                            listOf(
                                BOTTOM or RIGHT,
                                LEFT or CENTER_VERTICAL,
                                RIGHT or CENTER_VERTICAL,
                                LEFT or BOTTOM,
                                TOP or CENTER_HORIZONTAL
                            )
                        )
                    }
                    SystemClock.sleep(400)
                }
            }
        }

        btn_view.onClick {
            btn_view.showCoins(winCoins())
        }

        btn_2_view.onClick {
            btn_2_view.showCoins(winCoins())
        }

        btn_3_view.onClick {
            btn_3_view.showCoins(winCoins())
        }

        commander<GameFragmentCommander> { showEarnedCoins(viewModel.getEarnedCoins()) }
    }

    private fun winCoins() = { winType: CapGameView.CoinType, view: CapGameView ->
        view.postDelayed({
            view.hideCoins()
        }, 1000)

        if (winType == CapGameView.CoinType.EMPTY) {

        } else {
            winDialog.show(activity!!.supportFragmentManager)
        }

        Unit
    }

    private fun updateCaps() {
        btn_view.apply {
            hideCoins()
            init(level)
        }
        btn_2_view.apply {
            hideCoins()
            init(level)
        }
        btn_3_view.apply {
            hideCoins()
            init(level)
        }
    }

    private fun animateView(gravitiesPairs: List<Int>) {
        val transitionSet = TransitionSet()
        transitionSet.addTransition(ChangeBounds().setPathMotion(ArcMotion()).setDuration(400).addTarget(btn_view))
        transitionSet.addTransition(ChangeBounds().setPathMotion(ArcMotion()).setDuration(400).addTarget(btn_2_view))
        transitionSet.addTransition(ChangeBounds().setPathMotion(ArcMotion()).setDuration(400).addTarget(btn_3_view))
        TransitionManager.beginDelayedTransition(scene_view, transitionSet)

        btn_view.layoutParams = (btn_view.layoutParams as FrameLayout.LayoutParams).apply {
            gravity = getParamsByStartAnimationCount(
                animationCount,
                gravitiesPairs,
                { animationCount++ },
                { animationCount = 0 })
        }
        btn_2_view.layoutParams = (btn_2_view.layoutParams as FrameLayout.LayoutParams).apply {
            gravity = getParamsByStartAnimationCount(
                animationCount1,
                gravitiesPairs,
                { animationCount1++ },
                { animationCount1 = 0 })
        }
        btn_3_view.layoutParams = (btn_3_view.layoutParams as FrameLayout.LayoutParams).apply {
            gravity = getParamsByStartAnimationCount(
                animationCount2,
                gravitiesPairs,
                { animationCount2++ },
                { animationCount2 = 0 })
        }
    }

    private fun getParamsByStartAnimationCount(
        animationCount: Int,
        gravitiesPairs: List<Int>,
        increment: () -> Unit,
        bodyZero: () -> Unit
    ): Int {
        return when (animationCount) {
            0 -> {
                increment.invoke()
                gravitiesPairs[0]
            }
            1 -> {
                increment.invoke()
                gravitiesPairs[1]
            }
            2 -> {
                increment.invoke()
                gravitiesPairs[2]
            }
            3 -> {
                increment.invoke()
                gravitiesPairs[3]
            }
            else -> {
                bodyZero.invoke()
                gravitiesPairs[4]
            }
        }
    }

    interface GameFragmentCommander {
        fun showEarnedCoins(coins: Int)
    }
}