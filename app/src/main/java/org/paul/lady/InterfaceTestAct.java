package org.paul.lady;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.view.View;
import org.paul.lady.R;
import org.paul.lib.base.BaseAct;
import org.paul.lib.manager.ThreadManager;

import java.util.HashMap;
import java.util.Map;

public class InterfaceTestAct extends BaseAct implements View.OnClickListener {
    @Override
    protected int getLayoutId() {
        return R.layout.act_interfacetest;
    }

    @Override
    protected void bindUi() {

        $(R.id.login).setOnClickListener(this);
        $(R.id.region).setOnClickListener(this);
        $(R.id.verify).setOnClickListener(this);
        $(R.id.unread).setOnClickListener(this);
//        $(R.id.login).setOnClickListener(this);
//        $(R.id.login).setOnClickListener(this);
//        $(R.id.login).setOnClickListener(this);

    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            popMsg((String)msg.obj);
        }
    };

    private void popMsg(String spec) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                login();
                break;
            case R.id.region:
                region();
                break;
            case R.id.verify:
                verify();
                break;
            case R.id.unread:
                break;
        }
    }

    private void verify() {
        ThreadManager.getThreadManager().submit(new Runnable() {
            @Override
            public void run() {
                String ret = netManager.get(String.format(getString(R.string.region_api), "v1", "DIC_CM_COUNTRY",
                        "en-US"));
                handler.obtainMessage(1,ret).sendToTarget();
            }
        });
    }

    private void region() {
        ThreadManager.getThreadManager().submit(new Runnable() {
            @Override
            public void run() {
                String ret = netManager.get(String.format(getString(R.string.region_api), "v1", "DIC_CM_COUNTRY",
                        "en-US"));
                handler.obtainMessage(1,ret).sendToTarget();
            }
        });
    }

    private void login() {
        ThreadManager.getThreadManager().submit(new Runnable() {
            @Override
            public void run() {
                String ret = netManager.post(String.format(getString(R.string.login_api), "v1"), loginParams());
                handler.obtainMessage(1,ret).sendToTarget();
            }
        });

    }

    private Map<String, Object> loginParams() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("username", "zhangtuo@mkt.com");
        ret.put("password", "Deep1234");
        ret.put("loginType", "EM");
        return ret;
    }


}
