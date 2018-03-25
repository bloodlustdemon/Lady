package org.paul.lib.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import org.paul.lib.BuildConfig;
import org.paul.lib.helper.UiHelper;
import org.paul.lib.manager.NetManager;

/**
 * AUTHOR Paul
 * DATE 2018/3/23
 */
public abstract class BaseAct extends AppCompatActivity {
    protected String TAG = getClass().getSimpleName();
    protected NetManager netManager = NetManager.getNetManager();
    protected Intent parentIntent;
    protected Bundle savedInstanceState;
    private boolean hasWater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentIntent = getIntent();
        this.savedInstanceState = savedInstanceState;
        setContentView(getLayoutId());
        bindUi();
    }

    protected abstract int getLayoutId();

    protected abstract void bindUi();

    protected final <V extends View> V $(int resId) {
        return (V) findViewById(resId);
    }

    protected final <V extends View> V $(View parent, int resId) {
        return (V) (parent.findViewById(resId));
    }

    protected final void startAct(Class clz) {
        startWithData(clz, null);
    }

    protected final void startWithData(Class clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (null != bundle) {
            intent.putExtras(bundle);
            Log.d(TAG, "启动 " + clz.getSimpleName() + " bundle参数：" + bundle.toString());
        }
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BuildConfig.DEBUG) {
            if (!hasWater && UiHelper.getUiManager().isWaterOn()) {
                ViewGroup viewGroup = getRootView(this);
                viewGroup.addView(UiHelper.getUiManager().initWater(this));
                hasWater = true;
            }
        }
    }

    private final ViewGroup getRootView(Activity activity) {
        return (ViewGroup) activity.findViewById(android.R.id.content);
    }
}
