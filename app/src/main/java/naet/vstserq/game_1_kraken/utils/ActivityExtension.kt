package com.ja_company.gameja.utils

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle

inline fun <reified T : Any> Activity.startActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {

    val intent = Intent(this, T::class.java)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivityForResult(intent, requestCode)
    }
}
