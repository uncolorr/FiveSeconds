package com.example.uncolor.fiveseconds;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by uncolor on 16.05.17.
 */

public class ShakeAnimator extends BaseViewAnimator {
    @Override
    protected void prepare(View target) {

         ObjectAnimator.ofFloat(target, "translationX", 0,25,-25,25,-25,25,15,-15,6,-6,0).setDuration(3000).start();

    }
}
