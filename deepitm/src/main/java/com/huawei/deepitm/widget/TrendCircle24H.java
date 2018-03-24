package com.huawei.deepitm.widget;

import android.content.Context;
import android.graphics.*;
import android.os.Build;
//import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.huawei.deepitm.R;

import java.util.*;

/**
 * 24H TrendMap
 *
 * @AUTHER feona
 * @CREATE 2018/3/1  16:24
 **/
public class TrendCircle24H extends TrendCircle {

    public TrendCircle24H(Context context) {
        super(context);
    }

    public TrendCircle24H(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TrendCircle24H(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    Paint paint = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        if (dataList.size()>0) {
            drawTrend(canvas, dataList);
        } else if (dataMap.size()>0) {
            drawTrend(canvas, dataMap);
        } else {
            drawDefault(canvas);
        }
    }

    private List<Map<Long, Float>> dataList=new ArrayList<>();
    private Map<Long, Float> dataMap=new HashMap<>();

    public void setData(List<Map<Long, Float>> data) {
        if (null != data) {
            this.dataList.clear();
            this.dataList.addAll(data);
            invalidate();
        }
    }

    public void setData(Map<Long, Float> data) {
        if (null != data) {
            this.dataMap.clear();
            this.dataMap.putAll(data);
            invalidate();
        }
    }

    private void drawTrend(Canvas canvas, List<Map<Long, Float>> data) {
        for (Map<Long, Float> m : data) {
            drawTrend(canvas, m);
        }
    }

    private void drawTrend(Canvas canvas, Map<Long, Float> m) {
        Float[] ret = new Float[m.size()];
        int i = 0;
        for (Long key : m.keySet()) {
            ret[i] = m.get(key);
            i++;
        }
        drawTrend(canvas, ret);
    }

    private void drawTrend(Canvas canvas, Float[] data) {
        int x = (getRight() - getLeft()) / 2/*+getLeft()*/;
        int y = (getBottom() - getTop()) / 2/*+getTop()*/;

        //半径
        float r = x * 0.9f;
        //内圆半径
        float rn = x * 0.6f;
        float l = r - rn;
        int size = data.length;
        //间隔角度
        double perAngle = 360 / size;
        //确认数据点
        Path path = new Path();
        path.moveTo(x, y - l * data[0] - rn);
        for (int i = 1; i < size; i++) {
            float z = l * data[i] + rn;//斜边长
            float a1 = 0, b1 = 0;
            double angle = perAngle * i;
            if (angle < 90) {
                a1 = (float) (Math.sin(perAngle * i * Math.PI / 180) * z + x);
                b1 = (float) (y - (Math.cos(perAngle * i * Math.PI / 180) * z));
            } else if (angle == 90) {
                a1 = x + z;
                b1 = y;
            } else if (angle < 180) {
                a1 = (float) (Math.cos(angle % 90 * Math.PI / 180) * z + x);
                b1 = (float) (y + Math.sin(angle % 90 * Math.PI / 180) * z);
            } else if (angle == 180) {
                a1 = x;
                b1 = y + z;
            } else if (angle < 270) {
                a1 = (float) (x - Math.sin(angle % 90 * Math.PI / 180) * z);
                b1 = (float) (y + Math.cos(angle % 90 * Math.PI / 180) * z);
            } else if (angle == 270) {
                a1 = x - z;
                b1 = y;
            } else if (angle < 360) {
                a1 = (float) (x - Math.cos(angle % 90 * Math.PI / 180) * z);
                b1 = (float) (y - Math.sin(angle % 90 * Math.PI / 180) * z);
            }

            path.lineTo(a1, b1);
        }
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth((float) 2.3);
        paint.setColor(Color.CYAN);
        paint.setStrokeJoin(Paint.Join.ROUND);
        path.lineTo(/*aTmp, bTmp, */x, y - l * data[0] - rn);
        canvas.drawPath(path, paint);
        if (!innerHasDrawn) {
            drawInner(canvas, x, y, r);
        }
    }

    private boolean innerHasDrawn;

    private void drawDefault(Canvas canvas) {
        int x = (getRight() - getLeft()) / 2/*+getLeft()*/;
        int y = (getBottom() - getTop()) / 2/*+getTop()*/;
        //半径
        float r = x * 0.9f;
        //内圆半径
        float rn = x * 0.6f;
        float l = r - rn;
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.lighter_gray));
//        canvas.drawCircle(x, y, r, paint);
        paint.setColor(Color.WHITE);
//        canvas.drawCircle(x, y, rn, paint);
        drawInner(canvas, x, y, r);
    }

    protected void drawInner(Canvas canvas, int x, int y, float r) {
        paint.reset();
        paint.setAntiAlias(true);
        String up1 = "18";
        String up2 = "BROADBAND";
        String down1 = "0";
        String down2 = "ISSUE";

        paint.setStrokeWidth(8.0f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.LTGRAY);
        float a2= x-(float)(Math.sin(60*Math.PI/180)*r*0.5);
        float b2 = y+(float)(Math.cos(60*Math.PI/180)*r*0.5);
        float a3 =x+(float)(Math.sin(60*Math.PI/180)*r*0.5);
        float b3=y-(float)(Math.cos(60*Math.PI/180)*r*0.5);
        paint.setStrokeWidth(4);
        canvas.drawLine(a2,b2,a3,b3,paint);
        paint.reset();
        paint.setAntiAlias(true);
        Rect boundsUp2 = new Rect();
        Rect boundsUp1 = new Rect();
        Rect boundsDown1 = new Rect();
        Rect boundsDown2 = new Rect();
        paint.getTextBounds(up2, 0, up2.length(), boundsUp2);
        paint.getTextBounds(up1, 0, up1.length(), boundsUp1);
        paint.setStrokeWidth(1);
        paint.setTextSize(getWidth() / 16);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(up1, x - 2 * boundsUp2.width(), y - r / 3, paint);
        canvas.drawText(up2, x - 2 * boundsUp2.width()/3, y - r / 6, paint);
        paint.getTextBounds(down1, 0, down1.length(), boundsDown1);
        paint.getTextBounds(down2, 0, down2.length(), boundsDown2);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText(down1, x - boundsDown1.width() / 4/*-getLeft()/2*/, y + r / 4, paint);
        paint.setTextSize(getWidth() / 16);
        canvas.drawText(down2, x - boundsDown1.width() / 4/*-getLeft()/2*/, y + r / 3 + boundsDown2.height() / 2, paint);
        innerHasDrawn=true;
    }

}
