package com.huawei.deepitm.widget;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.huawei.deepitm.R;

import java.util.Vector;

/**
 * @AUTHER feona
 * @CREATE 2018/3/8  9:46
 **/
public class TrendCircle extends View {

    public TrendCircle(Context context) {
        super(context);
    }

    public TrendCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TrendCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    private Vector<float[]> data;

    private float data;
    private float percentage=0.35f;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getSize(widthMeasureSpec);
        int height = getSize(heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }
        setMeasuredDimension(width, height);
    }

    protected int getSize(int measureSpec) {
        int ret = 100;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                ret = 100;
                break;
            case MeasureSpec.AT_MOST:
                ret = size;
                break;
            case MeasureSpec.EXACTLY:
                ret = size;
                break;
        }
        return ret;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (0!=percentage) {
            paint.reset();
            paint.setAntiAlias(true);
            drawCircle(canvas);
        } else {
            drawDefault(canvas);
        }
    }
    Paint paint = new Paint();
    private void drawDefault(Canvas canvas) {
        int x = (getRight() - getLeft()) / 2;
        int y = (getBottom() - getTop()) / 2;
        Log.d("Trend", "圆点坐标 x=" + x + ",y=" + y);
        Log.d("Trend", "right=" + getRight() + ",left=" + getLeft() + ",bottom=" + getBottom() + ",top=" + getTop());
        //半径
        float r = x * 0.9f;

//        double l = 2*Math.PI*r;


        paint.setStrokeWidth(8.0f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.LTGRAY);

        RectF oval = new RectF(x-r,y-r,x+r,y+r);

        Path path = new Path();
        float a1 = (float) (x - Math.sin(30 * Math.PI / 180) * r);
        float b1 = (float) (y + Math.cos(30 * Math.PI / 180) * r);
        path.moveTo(a1, b1);

        path.arcTo(oval, 120f, 300f);
        canvas.drawPath(path, paint);
        float a2= x-(float)(Math.sin(60*Math.PI/180)*r*0.5);
        float b2 = y+(float)(Math.cos(60*Math.PI/180)*r*0.5);
        float a3 =x+(float)(Math.sin(60*Math.PI/180)*r*0.5);
        float b3=y-(float)(Math.cos(60*Math.PI/180)*r*0.5);
        paint.setStrokeWidth(4);
        canvas.drawLine(a2,b2,a3,b3,paint);

        String name ="Data";
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        Rect bounds = new Rect();
        paint.getTextBounds(name, 0, name.length(), bounds);
        paint.setStrokeWidth(1);
        paint.setTextSize(getWidth()/13);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(name,x -2* bounds.width(),y+r,paint);
    }

    private void drawCircle(Canvas canvas) {

        drawDefault(canvas);
        int x = (getRight() - getLeft()) / 2;
        int y = (getBottom() - getTop()) / 2;
        //半径
        float r = x * 0.9f;
        paint = new Paint();
        paint.setStrokeWidth(12.0f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.orange));

        RectF oval = new RectF(x-r,y-r,x+r,y+r);
        Path path = new Path();
        float a1 = (float) (x - Math.sin(30 * Math.PI / 180) * r);
        float b1 = (float) (y + Math.cos(30 * Math.PI / 180) * r);
        path.moveTo(a1, b1);

        path.arcTo(oval, 120f, 300f*percentage);
        canvas.drawPath(path, paint);


        drawInner(canvas, x, y, r);
    }

    protected void drawInner(Canvas canvas, int x, int y, float r) {
        paint.reset();
        paint.setAntiAlias(true);
        String up= "Normal";
        String down1 = "35/50GB";
        String down2 = "REMAINING";

        Rect boundsUp = new Rect();
        Rect boundsDown1 = new Rect();
        Rect boundsDown2 = new Rect();
        paint.getTextBounds(up, 0, up.length(), boundsUp);
        paint.setStrokeWidth(1);
        paint.setTextSize(getWidth()/13);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(up,x-2*boundsUp.width(),y-r/6,paint);
        paint.getTextBounds(down1,0,down1.length(),boundsDown1);
        paint.getTextBounds(down2,0,down2.length(),boundsDown2);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(down1,x-boundsDown1.width()/4/*-getLeft()/2*/,y+r/4,paint);
        paint.setTextSize(getWidth()/16);
        canvas.drawText(down2,x-boundsDown1.width()/4/*-getLeft()/2*/,y+r/3+boundsDown2.height()/2,paint);
    }

    public void setPercentage(float percentage){
        this.percentage=percentage;
        invalidate();
    }

}
