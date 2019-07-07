package com.ja_company.gameja.ui.task

import android.os.Bundle
import android.util.Log
import android.view.View
import com.ja_company.gameja.ui.Dialog
import com.ja_company.gameja.utils.onClick
import kotlinx.android.synthetic.main.tasks_fragment_layout.*
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.ui.DialogBuilder
import naet.vstserq.game_1_kraken.ui.base.BaseFragment

class TasksFragment : BaseFragment() {
    override var fragmentTag = "TasksFragment"

    override var layoutResId = R.layout.tasks_fragment_layout

    private val noInternetConnectionDialog: Dialog by lazy {
        DialogBuilder.newBuilder()
            .buildNoInternetConnectionDialog(context!!)
            .yellowClick({})
    }

    private val notEnoughCoinsDialog: Dialog by lazy {
        DialogBuilder.newBuilder()
            .notEnoughCoins(context!!)
            .yellowClick({})
    }

    companion object {
        fun newInstance(): TasksFragment {
            return TasksFragment().apply { arguments = Bundle().apply {} }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        best_offer_view.onClick {
            try {
                notEnoughCoinsDialog.show(activity?.supportFragmentManager!!)
            } catch (e: Exception) {
                Log.d("", "")
            }
        }

        top_video_view.onClick {}

        thimble_game_view.onClick {}

        super_offer_view.onClick {}

        epic_offer_view.onClick {}

        rate_us_view.onClick {}

    }
}