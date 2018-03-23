package org.paul.lib.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * AUTHOR Paul
 * DATE 2018/3/23
 */
public abstract class BaseFrag extends Fragment {

    protected String TAG = getClass().getSimpleName();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindUi();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container,false);
    }

    public abstract int getLayoutId();

    protected abstract void bindUi();

    protected final <V extends View> V $(int resId) {
        return (V) (getActivity().findViewById(resId));
    }

    protected final <V extends View> V $(View view, int resId) {
        return (V) (view.findViewById(resId));
    }
    protected void startAct(Class clz) {
        startWithData(clz, null);
    }
    protected final void startWithData(Class clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getContext(), clz);
        if (null != bundle) {
            intent.putExtras(bundle);
            Log.d(TAG, "启动 " + clz.getSimpleName() + " bundle参数：" + bundle.toString());
        }
        startActivity(intent);
    }
}
