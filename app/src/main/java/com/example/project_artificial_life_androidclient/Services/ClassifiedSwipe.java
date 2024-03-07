package com.example.project_artificial_life_androidclient.Services;

import android.view.MotionEvent;

public class ClassifiedSwipe {

    public ClassifiedSwipe(MotionEvent fromPoint,MotionEvent toPoint, float distanceX, float distanceY)
    {
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.distanceX = distanceX;
        this.distanceY = distanceY;
    }


    public static final int RandomSwipe = 0;
    public static final int SwipeUp = 1;
    public static final int SwipeDown = 2;
    public static final int SwipeRight = 3;
    public static final int SwipeLeft = 4;


    public int defineSwipe(int allowableError)
    {
        float DeltaX = toPoint.getX() - fromPoint.getX();
        float DeltaY = toPoint.getY() - fromPoint.getY();
        if(Math.abs(DeltaY) > Math.abs(DeltaX))
        {
            if(Math.abs(DeltaY) <= allowableError);
            else if(DeltaY > 0) return SwipeUp;
            else if(DeltaY < 0) return SwipeDown;
        }
        else
        {
            if(Math.abs(DeltaX) <= allowableError);
            else if(DeltaX > 0) return SwipeRight;
            else if(DeltaX < 0) return SwipeLeft;
        }
        return RandomSwipe;
    }


    private MotionEvent fromPoint;
    private MotionEvent toPoint;

    private float distanceX;
    private float distanceY;

}
