package naet.vstserq.game_1_kraken.ui.main.rewards

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ja_company.gameja.utils.onClick
import kotlinx.android.synthetic.main.rewards_fragment_layout.*
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RewardFragment : BaseFragment() {

    val viewModel: RewardsFragmentViewModel by viewModel()

    override var fragmentTag = "GameFragment"

    override var layoutResId = R.layout.rewards_fragment_layout

    companion object {
        fun newInstance(): RewardFragment {
            return RewardFragment().apply { arguments = Bundle().apply {} }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.giftsLists.observe(this, Observer {
            psn_gifts_view.init(R.string.psn_card, it.filter { it.backgroundImageResId == R.mipmap.ic_playstation })
            xbox_gifts_view.init(R.string.xbox_card, it.filter { it.backgroundImageResId == R.mipmap.ic_xbox })
            amazon_gifts_view.init(R.string.amazon_card, it.filter { it.backgroundImageResId == R.mipmap.ic_amazon })
            pay_pal_gifts_view.init(R.string.pay_pal_card, it.filter { it.backgroundImageResId == R.mipmap.ic_paypal })
            ebay_gifts_view.init(R.string.ebay_card, it.filter { it.backgroundImageResId == R.mipmap.ic_ebay })
        })

        viewModel.generateGiftsList()

        play_btn.onClick { commander<RewardsFragmentCommander> { openGameFragment() } }
    }

    interface RewardsFragmentCommander {
        fun openGameFragment()
    }
}