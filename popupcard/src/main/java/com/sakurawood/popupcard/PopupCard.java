package com.sakurawood.popupcard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
    /**
     * 设置是否是倒尖角
     */
    private boolean down;
    /**
     * 设置是否有尖角
     */
    private boolean caret;
    /**
     * 设置是否加载默认动画
     */
    private boolean defaultAnime;

    private boolean open;

    private boolean outSideClick;

    private PopupContainer popupContainer;

    private Activity activity;

    public static int FROM_DOWN = 4;
    public static int FROM_UP = 2;
    public static int FROM_LEFT = 1;
    public static int FROM_RIGHT = 3;
    public static int TARGET = 5;

    private int style;

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

    public PopupCard setOutSideClick(boolean outSideClick) {
        this.outSideClick = outSideClick;
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

    public PopupCard setActivity(Activity activity) {
        this.activity = activity;
        this.parent = (ViewGroup) getRootView(activity).getParent();
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

    public PopupCard setDown(boolean down) {
        this.down = down;
        return this;
    }

    public PopupCard setCaret(boolean caret) {
        this.caret = caret;
        return this;
    }

    public PopupCard setDefaultAnime(boolean defaultAnime) {
        this.defaultAnime = defaultAnime;
        return this;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void addViews(View viewGroup) {

        Log.e("layoutparams", width + "    " + height);

        PopcardBean popcardBean = new PopcardBean();

        popcardBean.setHeight(height);
        popcardBean.setWidth(width);
        //是否有尖角
        if (!caret) {
            //直接让其偏移出绘制区域，也就没有尖角了
            popcardBean.setX(width + width / 2);
            y = 0;
        } else {
            //x代表的是尖角的偏移量
            if (!down) {
                popcardBean.setX(x + width / 2);
            } else {
                popcardBean.setX(width / 2 - x);
            }
            popcardBean.setY(y);
            popcardBean.setW(w);
            popcardBean.setRadis(radis);
        }
        popcardBean.setRound(round);
        popcardBean.setPicid(resid);
        popcardBean.setColor(color);

        PopupCardView popcard = new PopupCardView(context, popcardBean);
        popcard.setPadding(ConvertUtils.dp2px(context, 0),
                ConvertUtils.dp2px(context, 0),
                ConvertUtils.dp2px(context, 0),
                ConvertUtils.dp2px(context, 0));
        addView(popcard);

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT);
        MarginLayoutParams marginLayoutParams = new MarginLayoutParams(layoutParams);
        //设置layout参数，合适的padding使得内容刚好在card里
        if (!down) {
            //尖角向上或者向下的情况都不一样，需要不同的计算方式
            viewGroup.setLayoutParams(marginLayoutParams);
            viewGroup.setPadding(ConvertUtils.dp2px(context, round / 2 + 2),
                    ConvertUtils.dp2px(context, y + round / 2 + 2),
                    ConvertUtils.dp2px(context, round / 2 + 2),
                    ConvertUtils.dp2px(context, round / 2));
            addView(viewGroup);
        } else {
//            如果向下的话，得将整个布局旋转，再将内容的布局旋转回来
            viewGroup.setLayoutParams(marginLayoutParams);
            viewGroup.setPadding(ConvertUtils.dp2px(context, round / 2 + 2),
                    ConvertUtils.dp2px(context, round / 2 + 2),
                    ConvertUtils.dp2px(context, round / 2 + 2),
                    ConvertUtils.dp2px(context, round / 2 + y));
            viewGroup.setRotation(180);
            addView(viewGroup);
            setRotation(180);
        }
    }

    public void close() {
        if (this.style == TARGET) {
            dismiss();
        } else {
            closeMenu();
        }
    }

    public void dismiss() {
        if (this != null) {
            this.removeAllViews();
        }
        if (popupContainer != null) {
            popupContainer.removeView(this);
        }
        if (parent != null) {
            parent.removeView(popupContainer);
        }
        removeAllViewsInLayout();
    }

    public void showContentAt(View target, View content) {
        this.style = TARGET;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ConvertUtils.dp2px(context, width), ConvertUtils.dp2px(context, height));
        int[] location = new int[2];
        target.getLocationInWindow(location);
        int x = location[0];
        int y = location[1];
        LogUtils.e("location", x + "   " + y);
        if (this.locationX == 0 || this.locationY == 0) {
            LogUtils.e("location", target.getHeight() + "    " + target.getWidth());
            //尖角向上和向下的显示位置是不一样的

            //如果x大于布局的二分之一，则将其定位在目标view的正上方或正下方
            if (x + target.getWidth() / 2 > ConvertUtils.dp2px(context, width / 2)
                    && getWindowDimen().mWidth - (x + target.getWidth()) < width / 2) {
                if (this.radis == 0) {
                    this.radis = 90;//如果用户没有指定尖角角度，正上方或正下方时，尖角角度设为90度
                }
                x = target.getWidth() / 2 + x - ConvertUtils.dp2px(context, width / 2);
            } else if (x < width / 2) {
                x = 5;
            } else if (getWindowDimen().mWidth - (x + target.getWidth()) < width / 2) {
                x = getWindowDimen().mWidth - 5 - width;
            }
            if (!down) {
                super.setX(x);
                super.setY(y + target.getHeight() - (getWindowDimen().mHeight - getAppDimen().mHeight));
            } else {
                super.setX(x);
                super.setY(y - ConvertUtils.dp2px(context, height) - (getWindowDimen().mHeight - getAppDimen().mHeight));
            }
        } else {
            super.setX(ConvertUtils.dp2px(context, locationX));
            super.setY(ConvertUtils.dp2px(context, locationY));
        }
        setLayoutParams(layoutParams);
        addViews(content);

        popupContainer = new PopupContainer(context, true);
        popupContainer.addView(this);
        popupContainer.setOutsideListener(this);
        //是否加载默认动画
        if (defaultAnime) {
            Anime.startAnimate1(this);
        }
        parent.addView(popupContainer);
    }

    public void openMenu(int style, View content) {
        dismiss();
        this.style = style;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ConvertUtils.dp2px(context, width), ConvertUtils.dp2px(context, height));
        int height = ConvertUtils.dp2px(context, this.height);
        int width = ConvertUtils.dp2px(context, this.width);
        super.setX(getWindowDimen().mWidth / 2 - width / 2);
        super.setY(getWindowDimen().mHeight);
        setLayoutParams(layoutParams);
        addViews(content);

        popupContainer = new PopupContainer(context, true);
        popupContainer.addView(this);
        popupContainer.setOutsideListener(this);
        //是否加载默认动画

        LogUtils.e("appdimen", getAppDimen().mHeight + "");
        if (defaultAnime) {
            Anime.startAnimate2(this, getAppDimen().mHeight, height);
        }
        setOpen(true);
        parent.addView(popupContainer);

    }

    public void closeMenu() {
        if (isOpen()) {
            Anime.startAnimate3(this, getAppDimen().mHeight - 150, 500);
            setOpen(false);
        } else {
            dismiss();
            popupContainer.setOutsideclick(false);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
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

    @Override
    public void onOutsideClick() {
        close();
    }

    private View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    /**
     * 获得整个屏幕的像素尺寸
     *
     * @return
     */
    private Dimension getWindowDimen() {
        Dimension dimen = new Dimension();
        Display disp = activity.getWindowManager().getDefaultDisplay();
        Point outP = new Point();
        disp.getSize(outP);
        dimen.mWidth = outP.x;
        dimen.mHeight = outP.y;
        return dimen;
    }

    /**
     * 获得整个app的像素尺寸（相比屏幕尺寸，除去了状态栏的高度）
     *
     * @return
     */
    private Dimension getAppDimen() {
        Dimension dimen = new Dimension();
        Rect outRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect);
        dimen.mWidth = outRect.width();
        dimen.mHeight = outRect.height();
        return dimen;
    }

    /**
     * 获得整个view绘制区域的像素尺寸（如果有toolbar，则除掉toolbar的高度）
     *
     * @return
     */
    private Dimension getViewDimen() {
        Dimension dimen = new Dimension();
        // 用户绘制区域
        Rect outRect = new Rect();
        activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(outRect);
        dimen.mWidth = outRect.width();
        dimen.mHeight = outRect.height();
        // end
        return dimen;
    }

    private class Dimension {
        public int mWidth;
        public int mHeight;

        public Dimension() {
        }
    }
}
