package com.huawei.deepitm.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.google.gson.Gson;
import com.huawei.deepitm.BuildConfig;
import com.huawei.deepitm.R;
import com.huawei.deepitm.bean.LoginBean;
import com.huawei.deepitm.bean.Region;
import com.huawei.deepitm.bean.RegionBean;
import com.huawei.deepitm.helper.Constants;
import org.paul.lib.base.BaseAct;
import org.paul.lib.base.BaseBean;
import org.paul.lib.helper.SPHelper;
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
    private String regionNumberStr = "+852";
    private String regionStr = "Hong Kong";

    @Override
    protected int getLayoutId() {
        return R.layout.act_login;
    }

    private View phoneForm;
    private View emailForm;
    private static final int LOGIN_SUCCESS = 0x11;
    private static final int LOGIN_FAILED = 0x12;
    private static final int NET_ERROR = 0x03;
    private static final int REGION_SUCCESS = 0x13;
    private static final int REGION_FAILED = 0x14;

    private String loginName, loginPassword;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOGIN_FAILED:
                    if (null != msg.obj)
                        Toast.makeText(LoginAct.this, ((LoginBean) msg.obj).getMessage(),
                                Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(LoginAct.this, R.string.net_error,
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case LOGIN_SUCCESS:
                    saveUserInfo((LoginBean) msg.obj);
                    startAct(HomeAct.class);
                    break;
                case REGION_SUCCESS:
                    saveRegionInfo((RegionBean) msg.obj);
                    break;
                case REGION_FAILED:
                    if (null != msg.obj)
                        Toast.makeText(LoginAct.this, ((RegionBean) msg.obj).getMessage(),
                                Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(LoginAct.this, R.string.net_error,
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void saveRegionInfo(RegionBean regionBean) {
        SPHelper.setString(this, "region", regionBean.getMessage());//FIXME
        SPHelper.setString(this, "regionNumber", regionBean.getMessage());//FIXME
        regionStr = regionBean.getMessage();
        regionNumberStr = regionBean.getMessage();
        updateRegionInfo();
    }

    private void saveUserInfo(LoginBean bean) {
        org.paul.lib.base.Constants.setToken(new Gson().toJson(bean.getData().getToken()));
        SPHelper.setString(LoginAct.this,"userId",bean.getData().getUserInfo().getAccountId());
        SPHelper.setString(LoginAct.this,"receiver",loginName);
        SPHelper.setString(LoginAct.this,"customerId",bean.getData().getUserInfo().getCustomerId());
    }

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPhoneNumber;
    private TextView region, regionNumber;
    private View mProgressView;
    private View mLoginFormView;
    private Button btnLogin;

    @Override
    protected void bindUi() {
        emailForm = $(R.id.email_login_form);
        phoneForm = $(R.id.phone_login_form);
        mEmailView = (AutoCompleteTextView) (emailForm.findViewById(R.id.email));
        mPasswordView = (EditText) (emailForm.findViewById(R.id.password));
        btnLogin = $(R.id.email_sign_in_button);
        mPhoneNumber = $(phoneForm, R.id.edit_phone);
        region = $(phoneForm, R.id.region);
        regionNumber = $(phoneForm, R.id.region_number);
        loadRegion();
    }

    private void loadRegion() {
        String region = SPHelper.getString(this, "region", "");
        String regionNumber = SPHelper.getString(this, "regionNumber", "");
        if (TextUtils.isEmpty(region)) {
            ThreadManager.getThreadManager().submit(new Runnable() {
                @Override
                public void run() {
                    RegionBean regionBean = netManager.get(BuildConfig.API_HOST + String.format(getString(R.string.region_api),
                            BuildConfig.API_LV, "DIC_CM_COUNTRY_CODE", "en-US"), RegionBean.class);
                    if (null != regionBean) {
                        if (regionBean.getCode() == Constants.SUCCESS) {
                            handler.obtainMessage(REGION_SUCCESS, regionBean).sendToTarget();
                        } else {
                            handler.obtainMessage(REGION_FAILED, regionBean).sendToTarget();
                        }
                    } else {
                        handler.sendEmptyMessage(REGION_FAILED);
                    }
                }
            });
        } else {
            this.regionStr = region;
            this.regionNumberStr = regionNumber;
        }

    }

    public void selectLoginType(View view) {
        if (CURRENT_LOGIN_TYPE.equals("EM")) {
            CURRENT_LOGIN_TYPE = "PC";
            phoneForm.setVisibility(View.VISIBLE);
            emailForm.setVisibility(View.GONE);
            btnLogin.setText(R.string.send_verification);
            btnLogin.setEnabled(false);
            updatePhoneLogin();
        } else if (CURRENT_LOGIN_TYPE.equals("PC")) {
            CURRENT_LOGIN_TYPE = "EM";
            phoneForm.setVisibility(View.GONE);
            emailForm.setVisibility(View.VISIBLE);
            btnLogin.setEnabled(true);
            btnLogin.setText(R.string.action_sign_in_short);
        }
    }

    private void updatePhoneLogin() {
        mPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    btnLogin.setEnabled(true);
                } else {
                    btnLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        if (TextUtils.isEmpty(region.getText().toString())) {
            updateRegionInfo();
        }
    }

    private void updateRegionInfo() {
        region.setText(regionStr);
        regionNumber.setText(regionNumberStr);
    }

    public void signIn(View view) {
        if (CURRENT_LOGIN_TYPE.equals("EM")) {
            attemptEmailLogin();
        } else {
            sendVerifyCode();
        }
    }

    private final int requestRegionCode = 0x33;

    private void sendVerifyCode() {
        Intent intent = new Intent(LoginAct.this, VerifyCodeAct.class);
        intent.putExtra("phoneNumber", mPhoneNumber.getText().toString());
        startActivityForResult(intent, requestRegionCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case requestRegionCode:
                //TODO               regionStr=data.getStringExtra("region");
                //TODO              regionNumberStr=data.getStringExtra("regionNumber");
                Region region = (Region) data.getSerializableExtra("region");

                updateRegionInfo();
                break;
        }
    }

    private boolean cancel;

    private void attemptEmailLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        if (BuildConfig.DEBUG) {
            mEmailView.setText("zhangtuo@mkt.com");
            mPasswordView.setText("Deep1234");
        }
        // Store values at the time of the login attempt.
        loginName = mEmailView.getText().toString();
        loginPassword = mPasswordView.getText().toString();
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(loginPassword) && !isPasswordValid(loginPassword)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(loginName)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(loginName)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
//            showProgress(true);

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

    }

    private Map<String, Object> createLoginParams() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("username", loginName);
        ret.put("password", loginPassword);
        ret.put("loginType", CURRENT_LOGIN_TYPE);
        return ret;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
}
