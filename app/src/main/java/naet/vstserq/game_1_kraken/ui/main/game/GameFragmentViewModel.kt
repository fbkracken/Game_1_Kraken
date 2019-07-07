package naet.vstserq.game_1_kraken.ui.main.game

import com.ja_company.gameja.utils.AppPreference
import naet.vstserq.game_1_kraken.di.BaseViewModel

class GameFragmentViewModel(private val prefs: AppPreference): BaseViewModel() {

    fun getEarnedCoins() = prefs.earnedCoins()

    fun getCoinIntoCap() = listOf(1, 2, 3).random()
}