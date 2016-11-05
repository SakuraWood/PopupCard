package com.sakurawood.popupcard;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by leesure on 16-11-5.
 */

public class Anime {
    public static void startAnimate1(View view) {
        PropertyValuesHolder pyhScaleX = PropertyValuesHolder.ofFloat("scaleX", 0.1f, 1.05f);
        PropertyValuesHolder pyhScaleY = PropertyValuesHolder.ofFloat("scaleY", 0.1f, 1.05f);
        ObjectAnimator animator_out = ObjectAnimator.ofPropertyValuesHolder(view, pyhScaleX,
                pyhScaleY); // 同时缩放X和Y
        animator_out.setInterpolator(new AccelerateDecelerateInterpolator());
        animator_out.setDuration(350);
        PropertyValuesHolder pyhScaleX2 = PropertyValuesHolder.ofFloat("scaleX", 1.05f, 1f);
        PropertyValuesHolder pyhScaleY2 = PropertyValuesHolder.ofFloat("scaleY", 1.05f, 1f);
        ObjectAnimator animator_in = ObjectAnimator.ofPropertyValuesHolder(view, pyhScaleX2,
                pyhScaleY2);

        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, alpha);
        animator.setDuration(450);
        animator_in.setInterpolator(new AccelerateDecelerateInterpolator());
        animator_in.setDuration(100);

        AnimatorSet animatorSet = new AnimatorSet();
        animator.start();
        animatorSet.playSequentially(animator_out, animator_in); // 按顺序执行两个动画
        animatorSet.start();
    }

    public static void startAnimate2(View view, int height, int viewheight) {

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,
                "translationY", view.getTranslationY(), height - viewheight);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();

    }

    public static void startAnimate3(View view, int height,int time) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view,
                "translationY", view.getTranslationY(), height);
        objectAnimator.setDuration(time);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
    }
}
