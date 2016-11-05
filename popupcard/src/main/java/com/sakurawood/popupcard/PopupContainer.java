package com.sakurawood.popupcard;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by leesure on 16-10-26.
 */

public class PopupContainer extends FrameLayout {
    private OnOutsideListener onOutsideListener;

    public PopupContainer(Context context) {
        super(context);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
    }

    public PopupContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PopupContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                onOutsideListener.onOutsideClick();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_SCROLL:
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }
        return true;
    }

    interface OnOutsideListener {
        void onOutsideClick();
    }

    public void setOutsideListener(OnOutsideListener outsideListener) {
        this.onOutsideListener = outsideListener;
    }
}
