package com.ja_company.gameja.utils

import android.content.Context
import android.preference.PreferenceManager

class AppPreference(context: Context) {
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    private companion object {
        const val BOOST_TIMER = "boost_timer"
        const val TUTORIAL = "tutorial"
        const val SAVE_EMAIL = "save_email"
        const val DIAMOND_COUNTER = "diamond_counter"
        const val SPIN_TIMER = "spin_timer"
        const val SHOW_EVERY_DAY_DIALOG = "show_every_day_dialog"
        const val SHOW_EVERY_DAY_DIALOG_BONUS_COUNT = "show_every_day_dialog_bonus_count"
        const val CURRENT_BONUS = "current_bonus"
        const val EARNED_COINS = "earned_coins"
    }

    fun earnedCoins(coins: Int) = prefs.edit().putInt(EARNED_COINS, coins)

    fun earnedCoins() = prefs.getInt(EARNED_COINS, 0)

    fun email(email: String) = prefs.edit().putString(SAVE_EMAIL, email).apply()

    fun email() = prefs.getString(SAVE_EMAIL, "") ?: ""

//    fun bonus(bonus: Types) = prefs.edit().putString(CURRENT_BONUS, bonus.name).apply()
//
//    fun bonus(): Types {
//        return when (prefs.getString(CURRENT_BONUS, "")!!) {
//            Types.DOLLARS_200.name -> Types.DOLLARS_200
//            Types.DOLLARS_300.name -> Types.DOLLARS_300
//            Types.DOLLARS_500.name -> Types.DOLLARS_500
//            else -> Types.DOLLARS_50
//        }
//    }
//
//
//    fun tutorialIsShowed() = prefs.getBoolean(TUTORIAL, false)
//
//    fun tutorialIsShowed(isShowed: Boolean = true) = prefs.edit().putBoolean(TUTORIAL, isShowed).apply()
//
//    fun diamondClickedCount(count: Int) = prefs.edit().putInt(DIAMOND_COUNTER, count).apply()
//
//    fun diamondClickedCount() = prefs.getInt(DIAMOND_COUNTER, ONE_MILLION)
//
//    fun setBoosTimerStart() {
//        prefs.edit().putLong(BOOST_TIMER, Date().time).apply()
//    }
//
//    fun canStartBoostTimer() = (Date().time - getBoostTimer()) > MINUTES_15
//
//    fun getBoostTimer() = prefs.getLong(BOOST_TIMER, -1)
//
//    fun setSpinTimerStart() {
//        prefs.edit().putLong(SPIN_TIMER, Date().time).apply()
//    }
//
//    fun canStartSpinView() = (Date().time - getSpinTimer()) > HOURS_3
//
//    fun getSpinTimer() = prefs.getLong(SPIN_TIMER, -1)
//
//    fun canShowEveryDayRewardDialog(bonus: (bonus: Int) -> Unit = {}): Boolean {
//        val time = prefs.getLong(SHOW_EVERY_DAY_DIALOG, 0L)
//        val timeOk = (Date().time - time) > MINUTES_1 && (Date().time - time) < MINUTES_3
////        val timeOk = (Date().time - time) > HOURS_24 && (Date().time - time) < HOURS_48
//        return if (getEveryDayOpenCount() == 0) {
//            setDailyRewardDialogTime()
//            bonus(REWORD_500)
//            true
//        } else if (getEveryDayOpenCount() in 1..6 && timeOk) {
//            bonus(REWORD_500)
//            setDailyRewardDialogTime()
//            true
//        } else if (getEveryDayOpenCount() > 6 && timeOk) {
//            bonus(REWORD_1000)
//            setDailyRewardDialogTime()
//            true
//        } else {
//            if ((Date().time - time) > HOURS_48)
//                resetEveryDayOpenCount()
//            return false
//        }
//    }
//
//    private fun setDailyRewardDialogTime() {
//        prefs.edit().putLong(SHOW_EVERY_DAY_DIALOG, Date().time).apply()
//        plusEveryDayOpen()
//    }
//
//    private fun resetEveryDayOpenCount() {
//        prefs.edit().putInt(SHOW_EVERY_DAY_DIALOG_BONUS_COUNT, 0).apply()
//    }
//
//    private fun plusEveryDayOpen() {
//        prefs.edit().putInt(SHOW_EVERY_DAY_DIALOG_BONUS_COUNT, getEveryDayOpenCount() + 1).apply()
//    }
//
//    private fun getEveryDayOpenCount() = prefs.getInt(SHOW_EVERY_DAY_DIALOG_BONUS_COUNT, 0)
}