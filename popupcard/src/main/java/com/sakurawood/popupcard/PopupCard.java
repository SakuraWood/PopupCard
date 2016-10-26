package com.sakurawood.popupcard;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by leesure on 16-10-24.
 */

public class PopupCard extends FrameLayout implements PopupContainer.OnOutsideListener {

    private Context context;
    private int resid;
    private ViewGroup parent;
    /**
     * the width of parent layout,it is also our popupcardview's width;
     */
    private int width;
    /**
     * the height of parent layout ,it is also our popupcardview's height;
     */
    private int height;
    /**
     * x is the middle point of triangle;
     */
    private int x = 0;
    /**
     * y is the height of triangle;
     */
    private int y = 0;
    /**
     * radis is the tilt of triangle;
     */
    private int radis;
    /**
     * w is the half width of triangle;
     */
    private int w;
    /**
     * round is the round of the popupcardview;
     */
    private int round;
    /**
     * the color of the popupcardview;
     */
    private int color;
    /**
     * the picture of the popupcardview;
     */
    private int picid;
    /**
     * the x of the popupcard;
     */
    private int locationX;
    /**
     * the y of the popupcard;
     */
    private int locationY;

    private PopupContainer popupContainer;

    public PopupCard(Context context) {
        super(context);
        this.context = context;
    }

    public PopupCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PopupCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public PopupCard setHeight(int height) {
        this.height = height;
        return this;
    }

    public PopupCard setWidth(int width) {
        this.width = width;
        return this;
    }

    public PopupCard setX(int x) {
        this.x = x;
        return this;
    }

    public PopupCard setY(int y) {
        this.y = y;
        return this;
    }

    public PopupCard setW(int w) {
        this.w = w;
        return this;
    }

    public PopupCard setRadis(int radis) {
        this.radis = radis;
        return this;
    }

    public PopupCard setRound(int round) {
        this.round = round;
        return this;
    }

    public PopupCard setColor(int color) {
        this.color = color;
        return this;
    }

    public PopupCard setPic(int pic) {
        this.resid = pic;
        return this;
    }

    public PopupCard setLocationX(int locationX) {
        this.locationX = locationX;
        return this;
    }

    public PopupCard setLocationY(int locationY) {
        this.locationY = locationY;
        return this;
    }

    public void addViews(ViewGroup viewGroup) {

        Log.e("layoutparams", width + "    " + height);

        PopcardBean popcardBean = new PopcardBean();

        popcardBean.setHeight(height);
        popcardBean.setWidth(width);
        popcardBean.setX(x);
        popcardBean.setY(y);
        popcardBean.setW(w);
        popcardBean.setRadis(radis);
        popcardBean.setRound(round);
        popcardBean.setPicid(resid);
        popcardBean.setColor(color);

        PopupCardView popcard = new PopupCardView(context, popcardBean);
        popcard.setPadding(ConvertUtils.dp2px(context, 0),
                ConvertUtils.dp2px(context, 0),
                ConvertUtils.dp2px(context, 0),
                ConvertUtils.dp2px(context, 0));
        addView(popcard);


        viewGroup.setPadding(ConvertUtils.dp2px(context, round / 2 + 2),
                ConvertUtils.dp2px(context, y + round / 2 + 2),
                ConvertUtils.dp2px(context, round / 2 + 2),
                ConvertUtils.dp2px(context, round / 2 + 2));

        addView(viewGroup);
    }

    public void dismiss() {
        this.removeAllViews();
        popupContainer.removeView(this);
        parent.removeView(popupContainer);
        removeAllViewsInLayout();
    }

    public void showContentAt(View target, ViewGroup content, ViewGroup parent) {
        this.parent = parent;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ConvertUtils.dp2px(context, width), ConvertUtils.dp2px(context, height));
        int[] location = new int[2];
        if (this.locationX == 0 || this.locationY == 0) {
            target.getLocationInWindow(location);
            int x = location[0];
            int y = location[1];
            Log.e("popupcard", ConvertUtils.px2dp(context, x) + "   " + ConvertUtils.px2dp(context, y));

            super.setX(x);
            super.setY(y);
        } else {
            Log.e("popupcard", locationX
                    + "   " + locationY);
            super.setX(ConvertUtils.dp2px(context, locationX));
            super.setY(ConvertUtils.dp2px(context, locationY));
        }
        setLayoutParams(layoutParams);

        popupContainer = new PopupContainer(context);
        popupContainer.addView(this);
        popupContainer.setOutsideListener(this);
        parent.addView(popupContainer);
        addViews(content);
        Log.e("popupcard", target.getLeft() + "   " + target.getTop() + "   "
                + target.getRight() + "    " + target.getBottom());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
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

    @Override
    public void onOutsideClick() {
        dismiss();
    }


}
