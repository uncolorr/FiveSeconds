package com.example.uncolor.fiveseconds;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by uncolor on 16.05.17.
 */

public class FadeInAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {
        ObjectAnimator.ofFloat(target,"alpha",0,1).setDuration(1000).start();
    }
}
