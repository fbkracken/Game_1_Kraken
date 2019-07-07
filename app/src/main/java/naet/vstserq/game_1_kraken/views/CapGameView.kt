package naet.vstserq.game_1_kraken.views

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.daimajia.androidanimations.library.YoYo
import com.ja_company.gameja.utils.HideCoinsAnim
import com.ja_company.gameja.utils.MyRotateCapAnimator
import kotlinx.android.synthetic.main.cap_game_layout.view.*
import naet.vstserq.game_1_kraken.R
import kotlin.concurrent.thread


class CapGameView : FrameLayout {

    enum class CoinType { CONTAINS, EMPTY }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context?) {
        inflate(context, R.layout.cap_game_layout, this)
    }

    fun init(level: Int): CapGameView {
        coin_view.visibility = View.VISIBLE
        when (level) {
            1 -> coin_view.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.img_coins_1))
            2 -> coin_view.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.img_coins_2))
            else -> coin_view.setImageDrawable(ContextCompat.getDrawable(context, R.mipmap.img_coins_3))

        }
        return this
    }

    fun showCoins(body: (type: CoinType, view: CapGameView) -> Unit) {
        YoYo.with(MyRotateCapAnimator())
            .duration(600)
            .playOn(cap_view)

        body.invoke(if (coin_view.visibility == View.VISIBLE) CoinType.CONTAINS else CoinType.EMPTY, this)
    }

    fun hideCoins() {
        YoYo.with(HideCoinsAnim())
            .duration(600)
            .playOn(cap_view)
    }

    fun showCoinsAndHide() {
        cap_view.post {
            YoYo.with(MyRotateCapAnimator())
                .duration(600)
                .onEnd {
                    thread {
                        SystemClock.sleep(2000)
                        Handler(Looper.getMainLooper()).post {
                            hideCoins()
                        }
                    }
                }
                .playOn(cap_view)
        }
    }
}