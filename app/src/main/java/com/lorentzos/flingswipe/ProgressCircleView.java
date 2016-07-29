package com.lorentzos.flingswipe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import me.payge.swipecardview.R;

/**
 * 圆弧圆环经度view
 * Created by WZG on 2016/7/28.
 */
public class ProgressCircleView extends View {
    //    宽
    private int with;
    //    高
    private int hight;
    //    间距
    private int pading;
    //    小圆环半径
    private int radius;
    //    圆环宽度
    private int paintWith;
    //    圆环数
    private int pie;
    //    当前选中圆环
    private int currentPosition;
    //    正常颜色
    private int normalColor;
    //    选中颜色
    private int clickColor;
    //    画笔
    private Paint paint;


    public ProgressCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProgressCircleOldView);
        pading = a.getDimensionPixelOffset(R.styleable.ProgressCircleOldView_pading, 0);
        radius = a.getDimensionPixelOffset(R.styleable.ProgressCircleOldView_radius, 10);
        paintWith = a.getDimensionPixelOffset(R.styleable.ProgressCircleOldView_paintWith, 4);
        pie = a.getInt(R.styleable.ProgressCircleOldView_pie, 5);
        currentPosition = a.getInt(R.styleable.ProgressCircleOldView_currentPosition, 0);
        normalColor = a.getColor(R.styleable.ProgressCircleOldView_normalColor, Color.BLUE);
        clickColor = a.getColor(R.styleable.ProgressCircleOldView_clickColor, Color.RED);
        a.recycle();
        initPaint();
    }


    private void initPaint() {
        paint = new Paint();
        paint.setStrokeWidth(paintWith);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    public ProgressCircleView(Context context) {
        super(context);
        initPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float[] position = new float[2];
        float[] tan = new float[2];
        float distance;
        Path pathCircle = new Path();
        RectF rectF = new RectF(pading + radius, pading + radius, with - pading - radius, hight - pading - radius);
        pathCircle.arcTo(rectF, -90, 359);
        PathMeasure pathMeasure = new PathMeasure(pathCircle, false);
        for (int index = 0; index < pie; index++) {
            if (currentPosition == index) {
                paint.setColor(clickColor);
            } else {
                paint.setColor(normalColor);
            }
            float allLength = pathMeasure.getLength();
            distance = (allLength / pie) * (index);
            pathMeasure.getPosTan(distance, position, tan);
            canvas.drawCircle(position[0], position[1], radius, paint);
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        invalidate();
    }

    public int getPie() {
        return pie;
    }

    public void setPie(int pie) {
        this.pie = pie;
    }

    public int getPading() {
        return pading;
    }

    public void setPading(int pading) {
        this.pading = pading;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getPaintWith() {
        return paintWith;
    }

    public void setPaintWith(int paintWith) {
        this.paintWith = paintWith;
    }

    public int getNormalColor() {
        return normalColor;
    }

    public void setNormalColor(int normalColor) {
        this.normalColor = normalColor;
    }

    public int getClickColor() {
        return clickColor;
    }

    public void setClickColor(int clickColor) {
        this.clickColor = clickColor;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        with = getWidth();
        hight = getHeight();
    }
}
