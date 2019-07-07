package com.ja_company.gameja.utils

import android.view.View

fun View.onClick(body: (view: View) -> Unit): View {
    this.setOnClickListener(body)
    return this
}

fun Long.toTime(): String {
    val minutes = this / (60 * 1000) % 60
    val seconds = this / 1000 % 60

    fun getTimeString(time: Long) = if (time == 0L) "" else "${if (time < 10) "0$time" else time}"

    return "${if (minutes < 10) "0$minutes" else minutes}:${if (seconds < 10) "0$seconds" else seconds}"
}