package org.paul.lib.helper;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class UiHelper {


    private static UiHelper uiManager = new UiHelper();

    private UiHelper() {
    }

    public static UiHelper getUiManager() {
        return uiManager;
    }

    private boolean waterOn=false;//是否显示水印，默认显示，需要主动取消!!

    public boolean isWaterOn() {
        return waterOn;
    }

    public void setWaterOn(boolean waterOn) {
        this.waterOn = waterOn;
    }

    /**
     * 初始化水印视图
     */
    public final View initWater(Context context) {
        FrameLayout water = new FrameLayout(context);
        TextView leftUp = initWaterText(new TextView(context));
        TextView leftDown = initWaterText(new TextView(context));
        TextView rightUp = initWaterText(new TextView(context));
        TextView rightDown = initWaterText(new TextView(context));
        FrameLayout.LayoutParams leftUpParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams leftDownParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams rightUpParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams rightDownParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        leftUpParams.gravity = Gravity.LEFT | Gravity.TOP;
        leftUpParams.setMargins(50,50,20,20);
        leftUp.setLayoutParams(leftUpParams);
        leftDownParams.gravity = Gravity.LEFT | Gravity.BOTTOM;
        leftDownParams.setMargins(50,20,20,50);
        leftDown.setLayoutParams(leftDownParams);
        rightUpParams.gravity = Gravity.RIGHT | Gravity.TOP;
        rightUpParams.setMargins(20,50,50,20);
        rightUp.setLayoutParams(rightUpParams);
        rightDownParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        rightDownParams.setMargins(20,20,50,50);
        rightDown.setLayoutParams(rightDownParams);
        water.addView(leftUp);
        water.addView(rightUp);
        water.addView(leftDown);
        water.addView(rightDown);
        PackageManager pm = context.getPackageManager();
        String name = context.getApplicationInfo().loadLabel(pm).toString();//开发者调试id为空则使用appname作为水印
        leftUp.setText(name);
        rightUp.setText(name);
        leftDown.setText(name);
        rightDown.setText(name);
        return water;
    }

    public TextView initWaterText(TextView textView) {
        textView.setTextColor(Color.GRAY);
        textView.setTextSize(20);
        textView.setRotation(-40.0f);
        return textView;
    }

    /**
     * 根据手机分辨率从DP转成PX
     * @param context
     * @param dpValue
     * @return
     */
    public static float dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return  (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     * @param spValue
     * @return
     */
    public static float sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return  (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     * @param context
     * @param pxValue
     * @return
     */
    public static float px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     * @param pxValue
     * @return
     */
    public static float px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (pxValue / fontScale + 0.5f);
    }

}
