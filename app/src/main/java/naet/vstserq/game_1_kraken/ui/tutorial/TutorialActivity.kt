package naet.vstserq.game_1_kraken.ui.tutorial

import android.os.Bundle
import com.ja_company.gameja.ui.tutorial.TutorialPagerAdapter
import kotlinx.android.synthetic.main.tutorial_activity_layout.*
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.ui.base.BaseActivity
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.onPageChangeListener

class TutorialActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.tutorial_activity_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pager_view.adapter = TutorialPagerAdapter(supportFragmentManager)
        dots_view.attachViewPager(pager_view)

        pager_view.onPageChangeListener {
            onPageSelected {
                if (it == TutorialPagerAdapter.NUM_ITEMS - 1) btn_view.text = getString(R.string.get_started)
                else btn_view.text = getString(R.string.next)
            }
        }

        btn_view.onClick {
            if (tutorialFinished()) navigator.toMainActivity()
            else pager_view.setCurrentItem(pager_view.currentItem + 1, true)
        }
        skip_btn.onClick { navigator.toMainActivity() }
    }

    private fun tutorialFinished() = btn_view.text.contains(getString(R.string.get_started))
}
