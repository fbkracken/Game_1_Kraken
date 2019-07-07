package naet.vstserq.game_1_kraken.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.StringRes
import kotlinx.android.synthetic.main.gifts_list_layout.view.*
import naet.vstserq.game_1_kraken.GiftModel
import naet.vstserq.game_1_kraken.R

class GiftsListView : FrameLayout {

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
        inflate(context, R.layout.gifts_list_layout, this)
    }

    fun init(@StringRes title: Int, gifts: List<GiftModel>): GiftsListView {
        title_view.text = context.getString(title)
        gifts_container_view.removeAllViews()
        gifts.map { gifts_container_view.addView(GiftCardView(context).apply { init(it) }) }
        return this
    }
}