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
    private boolean outsideclick;

    public PopupContainer(Context context, boolean outsideclick) {
        super(context);
        this.outsideclick = outsideclick;
        ViewGroup.LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
    }

    public PopupContainer(Context context, AttributeSet attrs, boolean outsideclick) {
        super(context, attrs);
        this.outsideclick = outsideclick;
    }

    public PopupContainer(Context context, AttributeSet attrs, int defStyleAttr, boolean outsideclick) {
        super(context, attrs, defStyleAttr);
        this.outsideclick = outsideclick;
    }

    public boolean isOutsideclick() {
        return outsideclick;
    }

    public void setOutsideclick(boolean outsideclick) {
        this.outsideclick = outsideclick;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                onOutsideListener.onOutsideClick();
                LogUtils.e("popupcontainer", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.e("popupcontainer", "ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtils.e("popupcontainer", "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.e("popupcontainer", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_SCROLL:
                LogUtils.e("popupcontainer", "ACTION_SCROLL");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                break;
        }
        return this.outsideclick;
    }

    interface OnOutsideListener {
        void onOutsideClick();
    }

    public void setOutsideListener(OnOutsideListener outsideListener) {
        this.onOutsideListener = outsideListener;
    }
}
