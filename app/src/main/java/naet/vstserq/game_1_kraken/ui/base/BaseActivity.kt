package naet.vstserq.game_1_kraken.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.di.Navigator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseActivity : AppCompatActivity() {

    open val containerResId = R.id.fragment_container

    val navigator by inject<Navigator> { parametersOf(this@BaseActivity) }

    open fun getLayoutResId() = R.layout.base_activity_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
    }
}