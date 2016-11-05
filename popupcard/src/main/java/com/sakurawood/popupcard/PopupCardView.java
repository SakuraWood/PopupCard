package com.sakurawood.popupcard;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leesure on 16-10-24.
 */

public class PopupCardView extends View {
    private Context context;
    private int width;
    private int height;

    private int x;
    private int y;
    private int w;
    private int radis;
    private int round;
    private int resid;
    private int color;
    private boolean down;

    public PopupCardView(Context context) {
        super(context);
        this.context = context;
        setWillNotDraw(false);
    }

    public PopupCardView(Context context, PopcardBean popcardBean) {
        super(context);
        this.context = context;
        this.height = popcardBean.getHeight();
        this.width = popcardBean.getWidth();
        this.x = popcardBean.getX();
        this.y = popcardBean.getY();
        this.w = popcardBean.getW();
        this.radis = popcardBean.getRadis();
        this.round = popcardBean.getRound();
        this.color = popcardBean.getColor();
        this.resid = popcardBean.getPicid();
    }

    public PopupCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setWillNotDraw(false);

    }

    public PopupCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setWillNotDraw(false);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        setMeasuredDimension(
                measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        int alpha = 0xff;
        for (int i = 0; i < 2; i++) {
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            paint.setARGB(alpha, 0xf3, 0xf3, 0xf3);
            paint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL));
            alpha = alpha - 30;
            RectF rect = new RectF(ConvertUtils.dp2px(context, (float) (0.5 - i / 2)),
                    ConvertUtils.dp2px(context, y + i / 2),
                    ConvertUtils.dp2px(context, width - (float) (0.5 - i / 2)),
                    ConvertUtils.dp2px(context, height - (float) (0.5 - i / 2)));

            canvas.drawRoundRect(rect, ConvertUtils.dp2px(context, round), ConvertUtils.dp2px(context, round), paint);
            Path path2 = new Path();
            path2.moveTo(ConvertUtils.dp2px(context, x - w - i / 2), ConvertUtils.dp2px(context, y + i / 2));
            path2.lineTo(ConvertUtils.dp2px(context, x + w + i / 2), ConvertUtils.dp2px(context, y + i / 2));
            path2.lineTo(ConvertUtils.dp2px(context, (float) (x + (y / (Math.tan(Math.toRadians(radis)))))), ConvertUtils.dp2px(context, (float) (0.5 - i / 2)));
            path2.close();
            canvas.drawPath(path2, paint);
        }
        drawRect(canvas);
    }

    private void drawRect(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setAlpha(255);
        paint.setColor(color);

        RectF rect = new RectF(ConvertUtils.dp2px(context, (float) 1),
                ConvertUtils.dp2px(context, (float) (y + 1)),
                ConvertUtils.dp2px(context, (float) (width - 1)),
                ConvertUtils.dp2px(context, (float) (height - 1)));

        canvas.drawRoundRect(rect, ConvertUtils.dp2px(context, round), ConvertUtils.dp2px(context, round), paint);
        Path path2 = new Path();
        path2.moveTo(ConvertUtils.dp2px(context, x - w), ConvertUtils.dp2px(context, (float) (y + 1)));
        path2.lineTo(ConvertUtils.dp2px(context, x + w), ConvertUtils.dp2px(context, (float) (y + 1)));
        path2.lineTo(ConvertUtils.dp2px(context, (float) (x + (y / (Math.tan(Math.toRadians(radis)))))), ConvertUtils.dp2px(context, (float) 1));
        path2.close();
        canvas.drawPath(path2, paint);
    }
}
