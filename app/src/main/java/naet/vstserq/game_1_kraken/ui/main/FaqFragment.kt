package naet.vstserq.game_1_kraken.ui.main

import android.os.Bundle
import android.view.View
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.ui.base.BaseFragment

class FaqFragment : BaseFragment() {

    override var fragmentTag = "GameFragment"

    override var layoutResId = R.layout.faq_fragment_layout

    companion object {
        fun newInstance(): FaqFragment {
            return FaqFragment().apply { arguments = Bundle().apply {} }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}