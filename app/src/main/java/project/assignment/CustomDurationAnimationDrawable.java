package project.assignment;

import android.graphics.drawable.AnimationDrawable;
import android.os.SystemClock;

public class CustomDurationAnimationDrawable extends AnimationDrawable {

    private volatile int duration;
    private int currentFrame;

    public CustomDurationAnimationDrawable() {
        currentFrame = 0;
    }

    @Override
    public void run() {
        int n = getNumberOfFrames();
        currentFrame++;
        if (currentFrame >= n) {
            currentFrame = 0;
        }
        selectDrawable(currentFrame);
        scheduleSelf(this, SystemClock.uptimeMillis() + duration);
    }

    public void setDuration(int duration) {
        this.duration = duration;
        unscheduleSelf(this);
        selectDrawable(currentFrame);
        scheduleSelf(this, SystemClock.uptimeMillis()+duration);
    }
}
