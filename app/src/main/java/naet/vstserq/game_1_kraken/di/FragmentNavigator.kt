package naet.vstserq.game_1_kraken.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import naet.vstserq.game_1_kraken.ui.base.BaseActivity
import naet.vstserq.game_1_kraken.ui.base.BaseFragment
import naet.vstserq.game_1_kraken.R

class FragmentNavigator (private val activity: BaseActivity) {

    fun replaceTransaction(fragment: Fragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(activity.containerResId, fragment)
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    fun replaceTransaction(fragment: BaseFragment) {
        activity.supportFragmentManager.beginTransaction()
            .replace(activity.containerResId, fragment, fragment.fragmentTag)
            .addToBackStack(fragment.fragmentTag)
            .commitAllowingStateLoss()
    }

    fun addTransaction(fragment: BaseFragment, hideCurrent: Boolean = true) {
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.applyDefaultNextCustomAnimations()
            .add(activity.containerResId, fragment, fragment.fragmentTag)

        fragmentTransaction
            .addToBackStack(fragment.fragmentTag)
            .commitAllowingStateLoss()
    }

    fun getCurFragment(body: (fragment: androidx.fragment.app.Fragment) -> Unit) {
        val manager = activity.supportFragmentManager
        manager.getBackStackEntryAt(manager.backStackEntryCount - 1).name?.let {
            manager.findFragmentByTag(it)?.let { body(it) }
        }
    }

    //todo improve
    protected fun back() {
        activity.supportFragmentManager.let {
            if (it.backStackEntryCount > 1) it.popBackStack() else activity.finish()
        }
    }

    private fun FragmentTransaction.applyDefaultNextCustomAnimations(): FragmentTransaction {
        return this.apply {
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
        }
    }

    protected fun FragmentTransaction.applyDefaultBackCustomAnimations(): FragmentTransaction {
        return this.apply {
            setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
        }
    }
}