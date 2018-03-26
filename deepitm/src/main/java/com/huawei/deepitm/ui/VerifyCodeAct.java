package com.huawei.deepitm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.huawei.deepitm.R;
import com.huawei.deepitm.bean.Region;
import com.huawei.deepitm.bean.RegionBean;
import org.paul.lib.base.BaseAct;
import org.paul.lib.base.BaseBean;
import org.paul.lib.manager.ThreadManager;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class VerifyCodeAct extends BaseAct {
    @Override
    protected int getLayoutId() {
        return R.layout.act_vfcode;
    }

    @Override
    protected void bindUi() {

        Toolbar toolbar = $(R.id.toolbar);
        initToolBar(toolbar);
        String phoneNumber = parentIntent.getStringExtra("phoneNumber");
        TextView textView = $(R.id.tv_phone_number);
        textViewTimeRest=$(R.id.tv_count_time);
        textViewTimeRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendVerification(v);
            }
        });
        textViewTimeRest.setClickable(false);
        textView.setText(phoneNumber);
        handler.sendEmptyMessage(TIME_COUNT);

        loadData();
    }

    private void loadData() {
        ThreadManager.getThreadManager().submit(new Runnable() {
            @Override
            public void run() {
                //netManager.post()//TODO 区号
            }
        });
    }

    private final int TIME_COUNT = 0x11;
    private final int VERIFY_SUCCESS = 0x13;
    private final int VERIFY_FAILED = 0x14;
    private final int region_success=0x1a;
    private int timeRest=60;
    private TextView textViewTimeRest;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case TIME_COUNT:
                    if(timeRest>0) {
                        timeRest--;
                        textViewTimeRest.setText(String.format(getString(R.string.time_sec_count),timeRest));
                        handler.sendEmptyMessageDelayed(TIME_COUNT, 1000);
                    }
                    if(timeRest==0){
                        textViewTimeRest.setText(R.string.send_verification);
                        textViewTimeRest.setClickable(true);
                        handler.removeMessages(TIME_COUNT);
                    }
                    break;
                case VERIFY_SUCCESS:
                    Intent ret=new Intent();
                    ret.putExtra("region", (Region) msg.obj);
                    setResult(region_success,ret);
                    finish();
                    break;
            }
        }
    };

    private void initToolBar(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void sendVerification(View view){
        textViewTimeRest.setClickable(false);
        timeRest=60;
        textViewTimeRest.setText(String.format(getString(R.string.time_sec_count),timeRest));
        handler.sendEmptyMessage(TIME_COUNT);
    }

    public void submit(View view) {
        attemptVerify();
    }

    private void attemptVerify() {

    }
}
