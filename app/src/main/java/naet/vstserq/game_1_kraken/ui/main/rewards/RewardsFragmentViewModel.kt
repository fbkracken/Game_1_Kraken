package naet.vstserq.game_1_kraken.ui.main.rewards

import androidx.lifecycle.MutableLiveData
import naet.vstserq.game_1_kraken.GiftModel
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.di.BaseViewModel

class RewardsFragmentViewModel : BaseViewModel() {

    val giftsLists = MutableLiveData<List<GiftModel>>()

    fun generateGiftsList() {

        val psnCards = listOf(
            GiftModel(R.mipmap.ic_playstation, R.string.coin_25, R.string.psn_card, R.string.coin_50_000),
            GiftModel(R.mipmap.ic_playstation, R.string.coin_50, R.string.psn_card, R.string.coin_80_000),
            GiftModel(R.mipmap.ic_playstation, R.string.coin_100, R.string.psn_card, R.string.coin_120_000),
            GiftModel(R.mipmap.ic_playstation, R.string.coin_200, R.string.psn_card, R.string.coin_200_000),
            GiftModel(R.mipmap.ic_playstation, R.string.coin_500, R.string.psn_card, R.string.coin_300_000)
        )

        val xBoxCards = listOf(
            GiftModel(R.mipmap.ic_xbox, R.string.coin_25, R.string.xbox_card, R.string.coin_50_000),
            GiftModel(R.mipmap.ic_xbox, R.string.coin_50, R.string.xbox_card, R.string.coin_80_000),
            GiftModel(R.mipmap.ic_xbox, R.string.coin_100, R.string.xbox_card, R.string.coin_120_000),
            GiftModel(R.mipmap.ic_xbox, R.string.coin_200, R.string.xbox_card, R.string.coin_200_000),
            GiftModel(R.mipmap.ic_xbox, R.string.coin_500, R.string.xbox_card, R.string.coin_300_000)
        )

        val amazonCards = listOf(
            GiftModel(R.mipmap.ic_amazon, R.string.coin_25, R.string.amazon_card, R.string.coin_50_000),
            GiftModel(R.mipmap.ic_amazon, R.string.coin_50, R.string.amazon_card, R.string.coin_80_000),
            GiftModel(R.mipmap.ic_amazon, R.string.coin_100, R.string.amazon_card, R.string.coin_120_000),
            GiftModel(R.mipmap.ic_amazon, R.string.coin_200, R.string.amazon_card, R.string.coin_200_000),
            GiftModel(R.mipmap.ic_amazon, R.string.coin_500, R.string.amazon_card, R.string.coin_300_000)
        )

        val payPallCards = listOf(
            GiftModel(R.mipmap.ic_paypal, R.string.coin_25, R.string.pay_pal_card, R.string.coin_50_000),
            GiftModel(R.mipmap.ic_paypal, R.string.coin_50, R.string.pay_pal_card, R.string.coin_80_000),
            GiftModel(R.mipmap.ic_paypal, R.string.coin_100, R.string.pay_pal_card, R.string.coin_120_000),
            GiftModel(R.mipmap.ic_paypal, R.string.coin_200, R.string.pay_pal_card, R.string.coin_200_000),
            GiftModel(R.mipmap.ic_paypal, R.string.coin_500, R.string.pay_pal_card, R.string.coin_300_000)
        )

        val eBayCards = listOf(
            GiftModel(R.mipmap.ic_ebay, R.string.coin_25, R.string.ebay_card, R.string.coin_50_000),
            GiftModel(R.mipmap.ic_ebay, R.string.coin_50, R.string.ebay_card, R.string.coin_80_000),
            GiftModel(R.mipmap.ic_ebay, R.string.coin_100, R.string.ebay_card, R.string.coin_120_000),
            GiftModel(R.mipmap.ic_ebay, R.string.coin_200, R.string.ebay_card, R.string.coin_200_000),
            GiftModel(R.mipmap.ic_ebay, R.string.coin_500, R.string.ebay_card, R.string.coin_300_000)
        )


        giftsLists.value = arrayListOf<GiftModel>().apply {
            addAll(psnCards)
            addAll(xBoxCards)
            addAll(amazonCards)
            addAll(payPallCards)
            addAll(eBayCards)
        }
    }
}