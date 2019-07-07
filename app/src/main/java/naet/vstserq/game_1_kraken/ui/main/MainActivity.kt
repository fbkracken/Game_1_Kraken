package naet.vstserq.game_1_kraken.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.ja_company.gameja.ui.main.game.GameFragment.GameFragmentCommander
import com.ja_company.gameja.utils.AppPreference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import naet.vstserq.game_1_kraken.DataSource
import naet.vstserq.game_1_kraken.R
import naet.vstserq.game_1_kraken.di.MainActivityFragmentNavigator
import naet.vstserq.game_1_kraken.ui.base.BaseActivity
import naet.vstserq.game_1_kraken.ui.main.rewards.RewardFragment.RewardsFragmentCommander
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,
    RewardsFragmentCommander, GameFragmentCommander {

    val prefs: AppPreference by inject()

    val fragmentNavigator by inject<MainActivityFragmentNavigator> { parametersOf(this@MainActivity) }

    override val containerResId get() = R.id.container

    val dataSource: DataSource by inject()

    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        fragmentNavigator.openGameFragment()
        toolbar_title_view.text = getString(R.string.tasks)

        coins_count_view.init(prefs.earnedCoins())
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            finish()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.offer_btn -> {
                toolbar_title_view.text = getString(R.string.thimble_game)
                fragmentNavigator.openGameFragment()
            }
            R.id.rewards_btn -> {
                toolbar_title_view.text = getString(R.string.rewards)
                fragmentNavigator.openRewardsFragment()
            }
            R.id.profile_btn -> {
                toolbar_title_view.text = getString(R.string.my_profile)
                fragmentNavigator.openProfileFragment()
            }
            R.id.faq_btn -> {
                toolbar_title_view.text = getString(R.string.faq)
                fragmentNavigator.openFaqFragment()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showEarnedCoins(coins: Int) {
        coins_count_view.init(coins)
    }

    override fun openGameFragment() = fragmentNavigator.openGameFragment()
}
