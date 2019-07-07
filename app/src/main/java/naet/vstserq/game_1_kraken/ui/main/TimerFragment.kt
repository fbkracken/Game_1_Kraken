package com.ja_company.gameja.ui.main

import android.os.Bundle
import android.view.View
import com.ja_company.gameja.utils.Constants
import kotlinx.android.synthetic.main.timer_fragment_layout.*
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.ui.base.BaseFragment

class TimerFragment : BaseFragment() {

    override var fragmentTag = "TimerFragment"

    override var layoutResId = R.layout.timer_fragment_layout

    companion object {
        fun newInstance(): TimerFragment {
            return TimerFragment().apply { arguments = Bundle().apply {} }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timer_view.init(Constants.HOURS_1)
    }
}