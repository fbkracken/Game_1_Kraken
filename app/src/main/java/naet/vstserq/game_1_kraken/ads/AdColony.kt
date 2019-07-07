package naet.vstserq.game_1_kraken.ads

import android.app.Activity
import com.adcolony.sdk.AdColonyInterstitial
import com.adcolony.sdk.AdColonyInterstitialListener
import naet.vstserq.game_1_kraken.R

class AdColony(private val activity: Activity, private var listener: AdColonyInterstitialListener) {

    private var keyZoneId: String? = null
    private var keyAppId: String? = null
    var adColonyInterstitial: AdColonyInterstitial? = null

    init {
        keyAppId = activity.getString(R.string.ad_colony_id)
        keyZoneId = activity.getString(R.string.ad_colony_zone_id)
        keyAppId?.let {
            com.adcolony.sdk.AdColony.configure(activity, it, keyZoneId)
        }
        load()
    }


    val isInit: Boolean
        get() = keyZoneId != null && keyAppId != null


    fun setEvent(adColonyInterstitial: AdColonyInterstitialListener) {
        this.listener = adColonyInterstitial
    }


    fun load() {
        keyZoneId = null
        keyZoneId = activity.getString(R.string.ad_colony_zone_id)
        keyZoneId?.let {
            com.adcolony.sdk.AdColony.requestInterstitial(it, listener)
        }
    }

}