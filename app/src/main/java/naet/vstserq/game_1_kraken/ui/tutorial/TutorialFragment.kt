package naet.vstserq.game_1_kraken.ui.tutorial

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.tutorial_fragment_layout.*
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.ui.base.BaseFragment

class TutorialFragment : BaseFragment() {

    override var fragmentTag = "TutorialFragment"

    override var layoutResId = R.layout.tutorial_fragment_layout

    companion object {
        private const val KEY = "KEY"
        fun newInstance(position: Int): TutorialFragment {
            return TutorialFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY, position)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getInt(KEY)?.let {
            when (it) {
                0 -> {
                    image_view.setImageDrawable(ContextCompat.getDrawable(context!!, R.mipmap.img_tutorial_1))
                    title_view.text = getString(R.string.complete_tasks)
                    text_view.text = getString(R.string.use_your_phone_to_complete_tasks_anywhere_near_you)
                }
                1 -> {
                    image_view.setImageDrawable(ContextCompat.getDrawable(context!!, R.mipmap.img_tutorial_2))
                    title_view.text = getString(R.string.collect_coins)
                    text_view.text = getString(R.string.collect_coins_snap)
                }
                else -> {
                    image_view.setImageDrawable(ContextCompat.getDrawable(context!!, R.mipmap.img_tutorial_3))
                    title_view.text = getString(R.string.get_your_rewards)
                    text_view.text = getString(R.string.get_your_favorite)
                }
            }
        }
    }
}