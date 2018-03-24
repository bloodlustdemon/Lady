package com.huawei.deepitm.ui;

import android.view.View;
import com.huawei.deepitm.R;
import com.huawei.deepitm.widget.TopBar;
import org.paul.lib.base.BaseAct;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class ChatAct extends BaseAct {
    @Override
    protected int getLayoutId() {
        return R.layout.act_chat;
    }

    @Override
    protected void bindUi() {

        TopBar topbar = $(R.id.topbar_overview);
        topbar.setTitle(parentIntent.getStringExtra("sendTo"));
        topbar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        topbar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(LoginAct.class);
            }
        });

    }
}
