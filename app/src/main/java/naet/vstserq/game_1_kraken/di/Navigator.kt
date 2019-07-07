package naet.vstserq.game_1_kraken.di

import androidx.appcompat.app.AppCompatActivity
import naet.vstserq.game_1_kraken.ui.main.MainActivity
import com.ja_company.gameja.utils.startActivity

class Navigator(private val activity: AppCompatActivity) {

    fun toMainActivity() = activity.startActivity<MainActivity>()
}