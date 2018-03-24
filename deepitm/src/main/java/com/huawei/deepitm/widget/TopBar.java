package com.huawei.deepitm.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.deepitm.R;
import org.paul.lib.widget.ImageWithNumberView;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class TopBar extends LinearLayout {
    protected ImageWithNumberView left;
    private ImageView left2;
    protected ImageView right;
    private Button right2;
    protected TextView title;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.v_top_bar, this,true);
        init(context, attrs);
    }

    protected void init(final Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.topbar);
        Drawable drawable_left = typedArray.getDrawable(R.styleable.topbar_lt_img);
        Drawable drawable_left2 = typedArray.getDrawable(R.styleable.topbar_lt_img2);
        Drawable drawable_right = typedArray.getDrawable(R.styleable.topbar_rt_img);
        Drawable drawable_right2 = typedArray.getDrawable(R.styleable.topbar_rt_img2);
        typedArray.recycle();

        left = (ImageWithNumberView) findViewById(R.id.img_left);
        left2 = (ImageView) findViewById(R.id.img_left2);
        right = (ImageView) findViewById(R.id.img_right);
        right2 = (Button) findViewById(R.id.img_right2);
        title = (TextView) findViewById(R.id.tv_top);
        if (null == drawable_left2) {
            left2.setVisibility(View.INVISIBLE);
        } else {
            left2.setImageDrawable(drawable_left2);
        }
        if (null == drawable_left) {
            left.setVisibility(View.INVISIBLE);
        } else {
            left.setImageDrawable(drawable_left);
        }
        if (null == drawable_right) {
            right.setVisibility(View.INVISIBLE);
        } else {
            right.setImageDrawable(drawable_right);
        }
        if (null == drawable_right2) {
            right2.setVisibility(View.GONE);
        } else {
            right2.setBackgroundDrawable(drawable_right2);
        }

    }

    public void setTitle(String spec) {
        if (!TextUtils.isEmpty(spec)) {
            title.setText(spec);
        }
    }

    public void setTitleLeft(String spec) {
        if (!TextUtils.isEmpty(spec)) {
            title.setText(spec);
//            title.setGravity(Gravity.LEFT);
            title.setGravity(Gravity.CENTER_VERTICAL);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) title.getLayoutParams();
            layoutParams.setMargins(100, 10, 0, 0);//4个参数按顺序分别是左上右下
//            layoutParams.setMarginStart(40);
            title.setLayoutParams(layoutParams);
            title.setTextColor(this.getResources().getColor(R.color.black));
            left.setVisibility(View.GONE);
        }
    }

    public void setTitleLeftwihtback(String spec) {
        if (!TextUtils.isEmpty(spec)) {
            title.setText(spec);
            title.setGravity(Gravity.CENTER_VERTICAL);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) title.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);//4个参数按顺序分别是左上右下
            title.setLayoutParams(layoutParams);
            title.setTextColor(this.getResources().getColor(R.color.black));

        }
    }

    public void setLeftListener(OnClickListener clickListener) {
        if (null != clickListener) {
            left.setOnClickListener(clickListener);
        }
    }

    public void setRightListener(OnClickListener clickListener) {
        if (null != clickListener) {
            right.setOnClickListener(clickListener);
        }
    }

    public void setNum(int num) {
        if (num > 0) {
            left.setNumber(num);
        }
    }
}
