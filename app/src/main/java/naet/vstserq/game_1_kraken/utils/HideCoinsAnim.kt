package com.ja_company.gameja.utils

import android.animation.ObjectAnimator
import android.view.View
import com.daimajia.androidanimations.library.BaseViewAnimator

class HideCoinsAnim : BaseViewAnimator() {
    public override fun prepare(target: View) {
        val x = target.paddingLeft.toFloat()
        val y = (target.height - target.paddingBottom).toFloat()
        animatorAgent.playTogether(
            ObjectAnimator.ofFloat(target, "rotation", -55F, 0F),
            ObjectAnimator.ofFloat(target, "alpha", 1F, 1F),
            ObjectAnimator.ofFloat(target, "pivotX", x, x),
            ObjectAnimator.ofFloat(target, "pivotY", y, y)
        )
    }
}