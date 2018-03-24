package org.paul.lib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import org.paul.lib.R;
import org.paul.lib.helper.UiHelper;

/**
 * AUTHOR Paul
 * DATE 2018/3/14
 */
public class TextWithNumberView extends android.support.v7.widget.AppCompatTextView {
    public TextWithNumberView(Context context) {
        this(context, null);
    }

    public TextWithNumberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextWithNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.img);
        textColor = typedArray.getColor(R.styleable.img_num_color, Color.WHITE);
        circleColor = typedArray.getColor(R.styleable.img_num_bg, Color.RED);

        typedArray.recycle();
    }

    private int num = 0;
    private float radius;
    private int circleColor;
    private int textColor;
    private float textSize;
    private int paddingRight, paddingTop;

    public void setNum(int num) {
        this.num = num;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (num > 0) {
            Paint paint = new Paint();
            Rect rect = new Rect();
            paint.setTextSize(getTextSize());
            paint.getTextBounds(getText().toString(), 0, getText().length(), rect);
            radius=rect.height()/2;
            textSize = num < 10 ? radius + 5 : radius;
            paddingRight = getPaddingRight();
            paddingTop = getPaddingTop();
            paint.setAntiAlias(true);
            paint.setColor(circleColor);
            paint.setStyle(Paint.Style.FILL);
            float x= (float) (getWidth()-paddingRight/2.5-radius);
            float y = (float) (getPaddingTop()+radius*2.5);
            canvas.drawCircle(x,y, radius, paint);

            TextPaint textPaint = new TextPaint();
            textPaint.setTextAlign(Paint.Align.CENTER);
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(textSize);
            String numberStr=String.valueOf(num);
            textPaint.getTextBounds(numberStr,0,numberStr.length(),rect);
            int textHeight = rect.height();
            canvas.drawText(numberStr,x,y+textHeight/2,textPaint);

        }
    }
}
