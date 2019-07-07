package naet.vstserq.game_1_kraken.ads

import android.content.Intent
import com.adcolony.sdk.AdColonyInterstitial
import com.adcolony.sdk.AdColonyInterstitialListener
import com.adcolony.sdk.AdColonyZone
import com.fyber.ads.videos.RewardedVideoActivity
import com.fyber.currency.VirtualCurrencyErrorResponse
import com.fyber.currency.VirtualCurrencyResponse
import com.fyber.requesters.RequestError
import com.fyber.requesters.VirtualCurrencyCallback
import naet.vstserq.game_1_kraken.ui.main.MainActivity

class AdManager(private val activity: MainActivity) : AdInterface, AdLifecycle {

    companion object {
        private const val OFFERWALL_FYBER_REQUEST_CODE = 8
        private const val VIDEO_FYBER_REQUEST_CODE_SPIN = 88
        private const val VIDEO_FYBER_REQUEST_CODE_OFFER = 888
    }


    private var adFyber: AdFyber
    private lateinit var adColony: AdColony
    private var isReward: Boolean = false
    private var coins: Int = 0


    init {

        adColony = AdColony(activity, object : AdColonyInterstitialListener() {
            override fun onRequestNotFilled(zone: AdColonyZone?) {
                showToast(activity, "AdColony: onRequestNotFilled, zone ${zone?.zoneID}")
            }

            override fun onOpened(ad: AdColonyInterstitial?) {

            }

            override fun onClosed(ad: AdColonyInterstitial?) {
                adColony.load()
                if (isReward) {
                    addCoins(AD_REWARD)
                }
            }

            override fun onIAPEvent(
                ad: AdColonyInterstitial?,
                product_id: String?, engagement_type: Int
            ) {

            }

            override fun onExpiring(ad: AdColonyInterstitial?) {
                showToast(activity, "AdColony")
            }

            override fun onLeftApplication(ad: AdColonyInterstitial?) {

            }

            override fun onClicked(ad: AdColonyInterstitial?) {

            }

            override fun onRequestFilled(ad: AdColonyInterstitial) {
                adColony.adColonyInterstitial = ad
                showToast(activity, "AdColony: ${ad.zoneID}")
            }
        })

        adFyber = AdFyber(activity, object : VirtualCurrencyCallback {
            override fun onError(virtualCurrencyErrorResponse: VirtualCurrencyErrorResponse) {
                showToast(activity, "Fyber: ${virtualCurrencyErrorResponse.errorMessage}")
            }

            override fun onSuccess(virtualCurrencyResponse: VirtualCurrencyResponse) {
                showToast(activity, "Fyber")
                val deltaOfCoins = virtualCurrencyResponse.deltaOfCoins.toInt()
                if (deltaOfCoins != 0 && deltaOfCoins != AD_REWARD) {
                    if (isReward) {
                        addCoins(deltaOfCoins)
                        return
                    }
                }
            }

            override fun onRequestError(requestError: RequestError) {
                showToast(activity, "Fyber: ${requestError.description}")
            }
        })

    }


    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == OFFERWALL_FYBER_REQUEST_CODE || requestCode == VIDEO_FYBER_REQUEST_CODE_OFFER) {
            adFyber.checkFyberRewards()
        }
        if (requestCode == VIDEO_FYBER_REQUEST_CODE_OFFER || requestCode == VIDEO_FYBER_REQUEST_CODE_SPIN) {
            adFyber.fyberIntentVideo = null
            adFyber.loadVideo()
            if (data == null) {
                return
            }
            when (data.getStringExtra(RewardedVideoActivity.ENGAGEMENT_STATUS)) {
                RewardedVideoActivity.REQUEST_STATUS_PARAMETER_FINISHED_VALUE -> if (requestCode == VIDEO_FYBER_REQUEST_CODE_OFFER) {
                    if (isReward) {
                        addCoins(AD_REWARD)
                        return
                    }
                }
                RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ABORTED_VALUE -> {
                }
                RewardedVideoActivity.REQUEST_STATUS_PARAMETER_ERROR -> {
                }
            }
        }
    }


    override fun onResume() {
        if (adColony.adColonyInterstitial == null || adColony.adColonyInterstitial!!.isExpired) {
            adColony.load()
        }

        if (isReward) {
            isReward = false
            if (coins != 0) {
                activity.showDialogGetClicks(coins)
            }
            coins = 0
        }
    }

    override fun onPause() {

    }


    override fun showAdColony(isReward: Boolean): Boolean {
        if (!adColony.isInit) {
            return false
        }
        this.isReward = isReward
        return if (adColony.adColonyInterstitial != null && !adColony.adColonyInterstitial!!.isExpired) {
            adColony.adColonyInterstitial!!.show()
        } else false
    }


    override fun showAdFyberOffer(): Boolean {
        if (!adFyber.isInit) {
            return false
        }
        val offerWallIntent = adFyber.fyberIntentOffer
        if (offerWallIntent != null) {
            activity.startActivityForResult(offerWallIntent, OFFERWALL_FYBER_REQUEST_CODE)
            return true
        }
        return false
    }


    override fun showAdFyberVideo(isReward: Boolean): Boolean {
        if (!adFyber.isInit) {
            return false
        }
        this.isReward = isReward
        val offerWallIntent = adFyber.fyberIntentVideo
        if (offerWallIntent != null) {
            activity.startActivityForResult(offerWallIntent, VIDEO_FYBER_REQUEST_CODE_OFFER)
            return true
        }
        return false
    }


    fun showAds(isReward: Boolean, types: Array<AdType>): Boolean {
        types.forEach {
            when (it) {
                AdType.ADCOLONY -> {
                    if (showAdColony(isReward)) {
                        return true
                    }
                }
                AdType.FYBER_VIDEO -> {
                    if (showAdFyberVideo(isReward)) {
                        return true
                    }
                }
                AdType.FYBER_OFFER -> {
                    if (showAdFyberOffer()) {
                        return true
                    }
                }
            }
        }
        return false
    }


    private fun addCoins(coins: Int) {
        this.coins = coins
        activity.addClicks(coins)
    }

}