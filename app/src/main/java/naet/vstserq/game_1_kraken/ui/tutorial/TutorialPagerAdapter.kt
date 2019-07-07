package com.ja_company.gameja.ui.tutorial

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import naet.vstserq.game_1_kraken.ui.tutorial.TutorialFragment

internal class TutorialPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    companion object {
        const val NUM_ITEMS = 3
    }

    override fun getCount() = NUM_ITEMS

    override fun getItem(position: Int) = TutorialFragment.newInstance(position)
}