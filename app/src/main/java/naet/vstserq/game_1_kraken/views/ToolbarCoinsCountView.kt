package naet.vstserq.game_1_kraken.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.toolbar_coins_count_layout.view.*
import naet.vstserq.game_1_kraken.R

class ToolbarCoinsCountView : FrameLayout {

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
        inflate(context, R.layout.toolbar_coins_count_layout, this)
    }

    fun init(coinsCount: Int): ToolbarCoinsCountView {
        coins_view.text = coinsCount.toString()
        return this
    }
}