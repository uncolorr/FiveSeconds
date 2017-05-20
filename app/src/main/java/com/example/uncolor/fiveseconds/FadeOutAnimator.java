package com.example.uncolor.fiveseconds;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by uncolor on 16.05.17.
 */

public class FadeOutAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {
        ObjectAnimator.ofFloat(target, "alpha", 1,0).setDuration(1000).start();
    }
}
