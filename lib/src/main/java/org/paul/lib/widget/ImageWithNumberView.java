package org.paul.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import org.paul.lib.R;

/**
 * AUTHOR Paul
 * DATE 2018/3/14
 */
public class ImageWithNumberView extends android.support.v7.widget.AppCompatImageView {

    public ImageWithNumberView(Context context) {
        this(context, null);
    }

    public ImageWithNumberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageWithNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.img);
        textColor = typedArray.getColor(R.styleable.img_num_color, Color.WHITE);
        circleColor = typedArray.getColor(R.styleable.img_num_bg, Color.RED);

        typedArray.recycle();
    }
    private int number;
    private int circleColor;
    private int textColor;
    private float textSize;
    private float circleRadius;
    private int paddingTop, paddingRight;
    private int width, height;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    public void setNumber(int number) {
        this.number = number;
        if (number > 0) {
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (number > 0) {
            drawCorner(canvas);
        }
    }

    private void drawCorner(Canvas canvas) {
        circleRadius = height / 6;
        textSize = number < 10 ? circleRadius + 5 : circleRadius;
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(circleColor);
        paint.setStyle(Paint.Style.FILL);
        float circleX = width - circleRadius - paddingRight / 2;
        float circleY = circleRadius + paddingTop / 2;
        canvas.drawCircle(circleX, circleY, circleRadius, paint);
        paint.reset();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(String.valueOf(number < 99 ? number : 99), circleX, circleY + textSize / 3, paint);
    }
}
