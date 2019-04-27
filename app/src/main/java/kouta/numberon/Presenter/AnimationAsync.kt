package kouta.numberon.Presenter

import android.view.View
import android.view.animation.Animation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun View.AnimationAsync(anim : Animation) {
    return suspendCoroutine { continuation ->
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation : Animation?) {
            }

            override fun onAnimationEnd(animation : Animation?) {
                continuation.resume(Unit)
            }

            override fun onAnimationRepeat(animation : Animation?) {
            }
        })
        this.startAnimation(anim)
    }
}