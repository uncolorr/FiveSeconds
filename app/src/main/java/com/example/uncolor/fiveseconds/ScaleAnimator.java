package com.example.uncolor.fiveseconds;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by uncolor on 16.05.17.
 */

public class ScaleAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {
                ObjectAnimator.ofFloat(target, "scaleX", 1.0f,1.1f,1.0f).setDuration(500).start();
                ObjectAnimator.ofFloat(target, "scaleY", 1.0f,1.1f,1.0f).setDuration(500).start();
    }
}
