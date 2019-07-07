package com.ja_company.gameja.utils

import android.animation.ObjectAnimator
import android.view.View
import com.daimajia.androidanimations.library.BaseViewAnimator

class RotationScene(private val prevRotation: Float, private val rotation: Float) : BaseViewAnimator() {
    public override fun prepare(target: View) {
        val x = (target.width / 2).toFloat()
        val y = (target.height / 2).toFloat()
        animatorAgent.playTogether(
            ObjectAnimator.ofFloat(target, "rotation", prevRotation, rotation),
            ObjectAnimator.ofFloat(target, "alpha", 1F, 1F),
            ObjectAnimator.ofFloat(target, "pivotX", x, x),
            ObjectAnimator.ofFloat(target, "pivotY", y, y)
        )
    }
}