package naet.vstserq.game_1_kraken

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class GiftModel(
    @DrawableRes val backgroundImageResId: Int, @StringRes val priceResId: Int,
    @StringRes val titleResId: Int, @StringRes val amountResId: Int
)