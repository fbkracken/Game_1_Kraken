package com.ja_company.gameja.views

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.widget.FrameLayout
import com.ja_company.gameja.utils.Constants
import com.ja_company.gameja.utils.toTime
import kotlinx.android.synthetic.main.timer_layout.view.*
import naet.vstserq.game_1_kraken.R
import org.jetbrains.anko.toast

class TimerView : FrameLayout {

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
        inflate(context, R.layout.timer_layout, this)
    }

    fun init(time: Long): TimerView {
        startTimer(time) {
            context.toast("timer finished")
        }
        return this
    }

    private fun startTimer(time: Long, finish: () -> Unit) =
        object : CountDownTimer(time, Constants.SECOND_1) {

            @SuppressLint("SetTextI18n")
            override fun onTick(diff: Long) {
                val time = diff.toTime()
                minute_one.text = time[0].toString()
                minute_two.text = time[1].toString()
                second_one.text = time[3].toString()
                second_two.text = time[4].toString()
            }

            override fun onFinish() {
                finish()
            }
        }.start()
}