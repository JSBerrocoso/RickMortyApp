package com.jsbs87.android.rickmorty.app.presentation.extension

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.jsbs87.android.rickmorty.app.R

fun View?.visible(visible: Boolean) {
    if (visible) this.visible()
    else this.gone()
}

fun View?.visible() {
    this ?: return
    this.visibility = View.VISIBLE
}

fun View?.invisible() {
    this ?: return
    this.visibility = View.INVISIBLE
}

fun View?.gone() {
    this ?: return
    this.visibility = View.GONE
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .placeholder(R.color.grey_light)
        .into(this)
}

fun Toolbar.animateSearchToolbar(
    numberOfMenuIcon: Int,
    containsOverflow: Boolean,
    show: Boolean
) {
    setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))

    if (show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val width: Int = this.getWidth() -
                    (if (containsOverflow) resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material) else 0) -
                    resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) * numberOfMenuIcon / 2
            val createCircularReveal: Animator = ViewAnimationUtils.createCircularReveal(
                this,
                if (isRtl(resources)) this.getWidth() - width else width,
                this.getHeight() / 2,
                0.0f,
                width.toFloat()
            )
            createCircularReveal.setDuration(250)
            createCircularReveal.start()
        } else {
            val translateAnimation =
                TranslateAnimation(0.0f, 0.0f, -this.getHeight() as Float, 0.0f)
            translateAnimation.duration = 220
            this.clearAnimation()
            this.startAnimation(translateAnimation)
        }
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val width: Int = this.getWidth() -
                    (if (containsOverflow) resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material) else 0) -
                    resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) * numberOfMenuIcon / 2
            val createCircularReveal: Animator = ViewAnimationUtils.createCircularReveal(
                this,
                if (isRtl(resources)) this.getWidth() - width else width,
                this.getHeight() / 2,
                width.toFloat(),
                0.0f
            )
            createCircularReveal.setDuration(250)
            createCircularReveal.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorAccent
                        )
                    )
                }
            })
            createCircularReveal.start()
        } else {
            val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
            val translateAnimation: Animation =
                TranslateAnimation(0.0f, 0.0f, 0.0f, -this.getHeight() as Float)
            val animationSet = AnimationSet(true)
            animationSet.addAnimation(alphaAnimation)
            animationSet.addAnimation(translateAnimation)
            animationSet.duration = 220
            animationSet.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorAccent
                        )
                    )
                }

                override fun onAnimationRepeat(animation: Animation) {}
            })
            startAnimation(animationSet)
        }
    }
}

private fun isRtl(resources: Resources): Boolean {
    return resources.configuration.layoutDirection === View.LAYOUT_DIRECTION_RTL
}


