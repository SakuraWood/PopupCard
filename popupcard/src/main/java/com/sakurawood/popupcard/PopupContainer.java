package com.sakurawood.popupcard;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
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

        layoutParams = getLayoutParams();
        Log.e("PopupContainer", ConvertUtils.px2dp(context, layoutParams.width)
                + "   " + ConvertUtils.px2dp(context, layoutParams.height));
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
                Log.e("handleaction", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("handleaction", "ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e("handleaction", "ACTION_CANCEL");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("handleaction", "ACTION_MOVE");
                break;
            case MotionEvent.ACTION_SCROLL:
                Log.e("handleaction", "ACTION_SCROLL");
                break;
            case MotionEvent.ACTION_OUTSIDE:
                Log.e("handleaction", "ACTION_OUTSIDE");
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
