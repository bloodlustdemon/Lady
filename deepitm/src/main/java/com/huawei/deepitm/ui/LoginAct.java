package com.huawei.deepitm.ui;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.huawei.deepitm.BuildConfig;
import com.huawei.deepitm.R;
import com.huawei.deepitm.bean.LoginBean;
import org.paul.lib.base.BaseAct;
import org.paul.lib.manager.ThreadManager;

import java.util.HashMap;
import java.util.Map;

/**
 * AUTHOR Paul
 * DATE 2018/3/23
 */
public class LoginAct extends BaseAct {

    private String CURRENT_LOGIN_TYPE = "EM";
    private boolean prepared;

    @Override
    protected int getLayoutId() {
        return R.layout.act_login;
    }

    private View phoneForm;
    private View emailForm;
    private static final int LOGIN_SUCCESS = 0x11;
    private static final int LOGIN_FAILED = 0x12;
    private static final int NET_ERROR = 0x03;

    private String loginName, loginPassword;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOGIN_FAILED:
                    break;
                case LOGIN_SUCCESS:
                    saveUserInfo((LoginBean) msg.obj);
                    startAct(HomeAct.class);
                    break;
            }
        }
    };

    private void saveUserInfo(LoginBean bean) {
    }

    @Override
    protected void bindUi() {
        emailForm = $(R.id.email_login_form);
        phoneForm = $(R.id.phone_login_form);


    }
    public void selectLoginType(View view) {
        if (CURRENT_LOGIN_TYPE.equals("EM")) {
            CURRENT_LOGIN_TYPE = "PC";
            phoneForm.setVisibility(View.VISIBLE);
            emailForm.setVisibility(View.GONE);
        } else if (CURRENT_LOGIN_TYPE.equals("PC")) {
            CURRENT_LOGIN_TYPE = "EM";
            phoneForm.setVisibility(View.GONE);
            emailForm.setVisibility(View.VISIBLE);
        }
    }
    public void signIn(View view) {
        if(prepared) {
            attemptLogin();
        }

        startAct(HomeAct.class);
//        startAct(VerifyCodeAct.class);
//        startAct(ResetPwdAct.class);
    }

    private void attemptLogin() {
        ThreadManager.getThreadManager().submit(new Runnable() {
            @Override
            public void run() {
                LoginBean bean = netManager.post(BuildConfig.API_HOST + String.format(getString(R.string.login_api), BuildConfig.API_LV),
                        createLoginParams(), LoginBean.class);
                if (null != bean) {
                    if (bean.getCode() == 0) {
                        handler.obtainMessage(LOGIN_SUCCESS, bean).sendToTarget();
                    } else {
                        handler.obtainMessage(LOGIN_FAILED, bean).sendToTarget();
                    }
                } else {
                    handler.obtainMessage(NET_ERROR).sendToTarget();
                }

            }
        });
    }

    private Map<String, Object> createLoginParams() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("username", loginName);
        ret.put("password", loginPassword);
        ret.put("loginType", CURRENT_LOGIN_TYPE);
        return ret;
    }

}
