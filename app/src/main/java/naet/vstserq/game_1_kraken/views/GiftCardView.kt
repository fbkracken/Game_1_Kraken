package naet.vstserq.game_1_kraken.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.gift_card_layout.view.*
import naet.vstserq.game_1_kraken.GiftModel
import naet.vstserq.game_1_kraken.R

class GiftCardView : FrameLayout {

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
        inflate(context, R.layout.gift_card_layout, this)
    }

    fun init(giftModel: GiftModel): GiftCardView {
        container_view.background = ContextCompat.getDrawable(context, giftModel.backgroundImageResId)
        price_view.text = context.getString(giftModel.priceResId)
        title_view.text = context.getString(giftModel.titleResId)
        amount_view.text = context.getString(giftModel.amountResId)
        return this
    }
}