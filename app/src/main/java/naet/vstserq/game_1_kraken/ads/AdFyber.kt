package naet.vstserq.game_1_kraken.ads

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.provider.Settings
import com.fyber.ads.AdFormat
import com.fyber.requesters.*
import naet.vstserq.game_1_kraken.R

class AdFyber(private val activity: Activity, private val mVirtualCurrencyCallback: VirtualCurrencyCallback) {

    var fyberIntentOffer: Intent? = null
        private set
    var fyberIntentVideo: Intent? = null
    private var mRewardedVideoRequester: RewardedVideoRequester? = null
    var settings: com.fyber.Fyber.Settings? = null
        private set
    private var keyFyberId: String? = null
    private var keyFyberSecurityTokenId: String? = null

    init {
        initFyber()
    }


    val isInit: Boolean
        get() = keyFyberId != null && keyFyberSecurityTokenId != null


    @SuppressLint("HardwareIds")
    private fun initFyber() {
        keyFyberId = activity.getString(R.string.fyber_id)
        keyFyberSecurityTokenId = activity.getString(R.string.fyber_sec_token)
        settings = com.fyber.Fyber.with(keyFyberId!!, activity)
            .withSecurityToken(keyFyberSecurityTokenId)
            .withUserId(
                Settings.Secure.getString(
                    activity.contentResolver,
                    "android_id"
                )
            ).start()
        settings?.notifyUserOnReward(false)
        settings?.notifyUserOnCompletion(false)
        OfferWallRequester.create(object : RequestCallback {
            override fun onAdAvailable(intent: Intent) {
                fyberIntentOffer = intent
            }

            override fun onAdNotAvailable(adFormat: AdFormat) {
                initFyber()
            }

            override fun onRequestError(requestError: RequestError) {
                initFyber()
            }
        }).request(activity)
        mRewardedVideoRequester = RewardedVideoRequester.create(object : RequestCallback {
            override fun onAdAvailable(intent: Intent) {
                // Store the intent that will be used later to show the video
                fyberIntentVideo = intent
            }

            override fun onAdNotAvailable(adFormat: AdFormat) {
                // Since we don't have an ad, it's best to reset the video intent
                fyberIntentVideo = null
            }

            override fun onRequestError(requestError: RequestError) {
                // Since we don't have an ad, it's best to reset the video intent
                fyberIntentVideo = null
            }
        })
        loadVideo()
    }


    fun loadVideo() {
        mRewardedVideoRequester.request(activity)

        //InterstitialRequester
    }


    fun checkFyberRewards() {
        VirtualCurrencyRequester.create(mVirtualCurrencyCallback).request(activity)
    }

}