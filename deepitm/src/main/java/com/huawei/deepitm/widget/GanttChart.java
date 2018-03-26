package com.huawei.deepitm.widget;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * *@auther skf
 * *@date 2018/3/12
 */
public class GanttChart extends View {
    public GanttChart(Context context) {
        this(context, null);
    }

    public GanttChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GanttChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
//        drawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG
//                | Paint.FILTER_BITMAP_FLAG);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        textPaint = new Paint();
    }

    private float scaleDistance = 20f;
    private Map<Long, Double> data = new HashMap<>();

    public void setData(Map<Long, Double> data) {
        this.data.clear();
        this.data.putAll(data);
        invalidate();
    }

    private Paint paint;
    private Paint textPaint;
    private PaintFlagsDrawFilter drawFilter;

    private int numY = 5;

    public void setNumY(int numY) {
        this.numY = numY;
        invalidate();
    }

    private int mHeight, mWidth;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
        mWidth = w;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpectMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpectSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpectMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpectSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpectMode == MeasureSpec.AT_MOST
                && heightSpectMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthSpectMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSpectSize);
        } else if (heightSpectMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpectSize, mHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        canvas.setDrawFilter(drawFilter);
        //
        paint.reset();
        textPaint.reset();
        paint.setAntiAlias(true);
        textPaint.setAntiAlias(true);
        if (data.size()>0) {
            drawCoordinate(canvas);
            drawPoint(canvas);
        }
    }

    /**
     * 画点
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {
        String spec = "CRITICAL";
        Rect rect = new Rect();
        textPaint.reset();
        textPaint.setAntiAlias(true);
        textPaint.getTextBounds(spec, 0, spec.length(), rect);
        //坐标原点
        float startX = getPaddingLeft() + dip2px(getContext(), textPaint.measureText(spec));
        float startY = mHeight - getPaddingBottom() - dip2px(getContext(), rect.height());

        float perX = (mWidth - startX - getPaddingRight()) / data.size();
        Set<Long> ts = data.keySet();
        Iterator<Long> iterator = ts.iterator();
        int per = ts.size() / handleSize();
        ArrayList<Double> vv = new ArrayList<>();
        while (iterator.hasNext()) {
            Long v = iterator.next();
//            Log.d("GANT","vv add v="+v);
            vv.add(data.get(v));
        }
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        Paint paint3 = new Paint();
//        paint.reset();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setStrokeWidth(6.0f);
        paint1.setStyle(Paint.Style.FILL);
        paint2.setStyle(Paint.Style.FILL);
        paint3.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(6.0f);
        paint2.setStrokeWidth(6.0f);
        paint3.setStrokeWidth(6.0f);
        paint1.setColor(Color.BLUE);
        paint2.setColor(Color.RED);
        paint3.setColor(Color.parseColor("#FF9701"));
        float lengthY = startY - getPaddingTop();
        float perLengthY = lengthY / numY;
        for (int i = 0; i < vv.size(); i++) {
            float x = startX + perX * i;
            Double v = vv.get(i) * 100;
            float y = 0;
//            Log.d("GANT","v="+v);
            if (v < 75) {
                y = startY - perLengthY - getPaddingTop();
//                paint.setColor(Color.BLUE);
                canvas.drawLine(x, y, x + 10, y, paint1);
            } else if (75 <= v && v <= 90) {
                y = startY - perLengthY * 2 - getPaddingTop();
//                paint.setColor(Color.RED);
                canvas.drawLine(x, y, x + 10, y, paint2);
            } else if (v > 90) {
                y = startY - perLengthY * 3 - getPaddingTop();
//                paint.setColor(Color.parseColor("#FF9701"));
                canvas.drawLine(x, y, x + 10, y, paint3);
            }
//            canvas.drawLine(x, y, x + 40, y, paint);
        }


    }

    /**
     * 画坐标系
     *
     * @param canvas
     */
    private void drawCoordinate(Canvas canvas) {

        String spec = "CRITICAL";
        Rect rect = new Rect();
        textPaint.getTextBounds(spec, 0, spec.length(), rect);

        //坐标原点
        float startX = getPaddingLeft() + dip2px(getContext(), textPaint.measureText(spec));
        float startY = mHeight - getPaddingBottom() - dip2px(getContext(), rect.height());
        Log.d("GANT", "坐标原点,x=" + startX + ",y=" + startY);
        //X轴长度
        float lengthX = mWidth - getPaddingRight() - startX;
        //Y轴长度
        float lengthY = startY - getPaddingTop();

        //确定需要多少X坐标
        int num = handleSize();

        float perLengthX = lengthX / num;
        float perLengthY = lengthY / numY;

        paint.setColor(Color.DKGRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);

        for (int i = 0; i < numY; i++) {
            float x = startX + scaleDistance;
            float sX = startX + lengthX;
            float y = startY - perLengthY * i - getPaddingTop();
            float sY = startY - perLengthY * i - getPaddingTop();
            if (i == 0) {
//                drawX(canvas, num, y, perLengthY);
                x = getPaddingLeft();
            }
            textPaint.setColor(Color.DKGRAY);
            drawY(canvas, i, y, perLengthX);
            canvas.drawLine(x, y, sX, sY, paint);
            Log.d("GANT", "第" + i + "行,x=" + x + ",y=" + y);
        }
    }

    private int type;
    private String specN = "CRITICAL";

    private void drawY(Canvas canvas, int i, float y, float perLengthX) {
        String spec = "";
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextSize(sp2px(getContext(), 14));
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        Rect rect = new Rect();
        switch (i) {
            case 1:
                spec = "<75%";
                break;
            case 2:
                spec = "75-90%";
                break;
            case 3:
                spec = ">90%";
                break;
            case 4:
                spec = "CRITICAL";
                break;
            case 0:
                spec = type == 0 ? "DAY" : (type == 1 ? "WEEK" : (type == 2 ? "MONTH" : "YEAR"));
                break;
        }
        textPaint.getTextBounds(spec, 0, spec.length(), rect);
        canvas.drawText(spec, getPaddingLeft(), y + sp2px(getContext(), rect.height() / (i == 0 ? 2 : 8)), textPaint);
        if (i == 0) {
            drawX(canvas, y + sp2px(getContext(), rect.height() / 2), perLengthX);
        }
    }

    /**
     * X 轴坐标点
     *
     * @param canvas     //     * @param num
     * @param y
     * @param perLengthX
     */
    private void drawX(Canvas canvas,/* int num,*/ float y, float perLengthX) {
        textPaint.reset();
        textPaint.setAntiAlias(true);
        int num = handleSize();
        Rect rect = new Rect();
        textPaint.getTextBounds(specN, 0, specN.length(), rect);
        float x = getPaddingLeft() + dip2px(getContext(), textPaint.measureText(specN));

        textPaint.setTextAlign(Paint.Align.LEFT);
        String spec = "";
        Set<Long> ts = data.keySet();
        Iterator<Long> iterator = ts.iterator();
        int per = ts.size() / num;
        ArrayList<Long> vv = new ArrayList<>();
        while (iterator.hasNext()) {
            Long v = iterator.next();
            vv.add(v);
        }
        textPaint.setTextSize(sp2px(getContext(), 14));
        textPaint.setColor(Color.DKGRAY);
        for (int i = 0; i < num; i++) {
            float startX = x + perLengthX * i;
//            float startY = y + sp2px(getContext(), rect.height() /*/ 2*/);
//            float startY = y -;
            spec = getXV(per, vv, i);
            canvas.drawText(spec, startX, y, textPaint);
        }

    }

    private String getXV(int per, ArrayList<Long> vv, int i) {
        String spec;
//        spec = ""+vv.get(i*per);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime(vv.get(i) * per);
        spec = sdf.format(date);
        return spec;
    }

    private int handleSize() {
        int ret = 6;
//        if (null != data) {
//            ret = data.size();
//            if (ret > 20) {
//                ret = ret / 6;
//            }
//        }
        return ret;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
