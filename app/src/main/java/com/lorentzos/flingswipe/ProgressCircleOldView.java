package com.lorentzos.flingswipe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by WZG on 2016/7/28.
 */
public class ProgressCircleOldView extends View {
    private int with;
    private int hight;
    private int pading = 50;
    private int radius = 10;
    private int paintWith = 4;
    private Paint paint;
    private float distance;
    private int currentPosition;

    public ProgressCircleOldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ProgressCircleOldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(paintWith);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }

    public ProgressCircleOldView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float[] position = new float[2];
        float[] tan = new float[2];
        Path pathCircle = new Path();
        pathCircle.addCircle(with / 2, hight / 2, hight / 2 - pading - radius, Path.Direction.CW);
        PathMeasure pathMeasure = new PathMeasure(pathCircle, false);
        for (int index = 0; index < 5; index++) {
            if (currentPosition == index) {
                paint.setColor(Color.RED);
            } else {
                paint.setColor(Color.BLUE);
            }
            float allLength = pathMeasure.getLength();
            distance = (allLength / 5) * (index + 1);
            pathMeasure.getPosTan(distance, position, tan);
            canvas.drawCircle(position[0], position[1], radius, paint);
        }
        paint.setColor(Color.BLUE);
        canvas.drawPath(pathCircle,paint);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        with = getWidth();
        hight = getHeight();
    }
}
