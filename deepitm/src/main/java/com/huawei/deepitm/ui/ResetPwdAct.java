package com.huawei.deepitm.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import com.huawei.deepitm.R;
import org.paul.lib.base.BaseAct;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class ResetPwdAct extends BaseAct {
    @Override
    protected int getLayoutId() {
        return R.layout.act_reset;
    }

    @Override
    protected void bindUi() {

        ViewStub emailReset=$(R.id.email_reset_form);
        emailReset.inflate();
        Toolbar toolbar = $(R.id.toolbar);
        initToolBar(toolbar);
    }
    private void initToolBar(Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
