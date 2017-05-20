package com.example.uncolor.fiveseconds;

import android.animation.ObjectAnimator;
import android.view.View;

import com.example.uncolor.fiveseconds.BaseViewAnimator;

/**
 * Created by uncolor on 16.05.17.
 */

public class PullAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {
                ObjectAnimator.ofFloat(target, "scaleX", 1, 1.25f, 0.75f, 1.15f, 1).setDuration(3000).start();
                ObjectAnimator.ofFloat(target, "scaleY", 1, 0.75f, 1.25f, 0.85f, 1).setDuration(3000).start();
    }
}
