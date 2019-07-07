package naet.vstserq.game_1_kraken.di

import naet.vstserq.game_1_kraken.ui.main.FaqFragment
import com.ja_company.gameja.ui.main.TimerFragment
import com.ja_company.gameja.ui.main.game.GameFragment
import naet.vstserq.game_1_kraken.ui.main.profile.ProfileFragment
import naet.vstserq.game_1_kraken.ui.main.rewards.RewardFragment
import com.ja_company.gameja.ui.task.TasksFragment

class MainActivityFragmentNavigator(private val fragmentNavigator: FragmentNavigator) {

    fun openTaskFragment() = fragmentNavigator.replaceTransaction(TasksFragment.newInstance())

    fun openGameFragment() = fragmentNavigator.replaceTransaction(GameFragment.newInstance())

    fun openFaqFragment() = fragmentNavigator.replaceTransaction(FaqFragment.newInstance())

    fun openProfileFragment() = fragmentNavigator.replaceTransaction(ProfileFragment.newInstance())

    fun openRewardsFragment() = fragmentNavigator.replaceTransaction(RewardFragment.newInstance())

    fun openTimerFragment() = fragmentNavigator.addTransaction(TimerFragment.newInstance())
}