package naet.vstserq.game_1_kraken.di

import naet.vstserq.game_1_kraken.ui.base.BaseActivity
import naet.vstserq.game_1_kraken.ui.main.game.GameFragmentViewModel
import naet.vstserq.game_1_kraken.ui.main.profile.ProfileFragmentViewModel
import naet.vstserq.game_1_kraken.ui.main.rewards.RewardsFragmentViewModel
import com.ja_company.gameja.utils.AppPreference
import naet.vstserq.game_1_kraken.DataSource
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { DataSource() }

    single { AppPreference(androidContext()) }

    viewModel { RewardsFragmentViewModel() }

    viewModel { GameFragmentViewModel(get()) }

    viewModel { ProfileFragmentViewModel(get()) }

    factory { (activity: BaseActivity) -> MainActivityFragmentNavigator(FragmentNavigator(activity)) }

    factory { (activity: BaseActivity) -> Navigator(activity) }
}
