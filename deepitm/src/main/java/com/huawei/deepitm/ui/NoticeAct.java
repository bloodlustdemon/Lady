package com.huawei.deepitm.ui;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import com.huawei.deepitm.R;
import com.huawei.deepitm.adapter.MsgAdapter;
import com.huawei.deepitm.adapter.NoticeAdapter;
import org.paul.lib.base.BaseAct;
import org.paul.lib.manager.ThreadManager;
import org.paul.lib.widget.TextWithNumberView;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class NoticeAct extends BaseAct {
    @Override
    protected int getLayoutId() {
        return R.layout.act_notice;
    }

    @Override
    protected void bindUi() {

         recyclerView = $(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        noticeAdapter = new NoticeAdapter(this);
        msgAdapter = new MsgAdapter(this);
        recyclerView.setAdapter(noticeAdapter);
        TabLayout tabLayout = $(R.id.tab_notice);
        Toolbar toolbar = $(R.id.toolbar);
        initTab(tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        loadData();

    }

    private final int NOTICE_SUCCESS = 0x11;
    private final int NOTICE_FAILED = 0x12;
    private NoticeAdapter noticeAdapter;
    private MsgAdapter msgAdapter;
    private RecyclerView recyclerView;
    private int currentIndex;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NOTICE_FAILED:
                    break;
                case NOTICE_SUCCESS:
                    break;
            }
        }
    };

    private void loadData() {
        ThreadManager.getThreadManager().submit(new Runnable() {
            @Override
            public void run() {
                //TODO 获取消息
            }
        });
    }

    private void initTab(TabLayout tabLayout) {
        TabLayout.Tab tab = tabLayout.newTab().setCustomView(inflateTab(tabLayout, 0));
        TabLayout.Tab tab1 = tabLayout.newTab().setCustomView(inflateTab(tabLayout, 1));
        TabLayout.Tab tab2 = tabLayout.newTab().setCustomView(inflateTab(tabLayout, 2));
        TabLayout.Tab tab3 = tabLayout.newTab().setCustomView(inflateTab(tabLayout, 3));
        tabLayout.addTab(tab);
        tabLayout.addTab(tab1);
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextWithNumberView) (tab.getCustomView())).setSelected(true);
                currentIndex = tab.getPosition();
                notifyAdapter();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextWithNumberView) (tab.getCustomView())).setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void notifyAdapter() {
        //TODO 切换tab更新adapter数据刷新recycler
//        recyclerView.removeAllViews();
        switch (currentIndex){
            case 0:
            case 1:
                recyclerView.setAdapter(noticeAdapter);
                noticeAdapter.notifyDataSetChanged();
                break;
            case 2:
            case 3:
                recyclerView.setAdapter(msgAdapter);
                msgAdapter.notifyDataSetChanged();
                break;
        }
    }


    private TextWithNumberView inflateTab(final TabLayout tabLayout, int i) {
        TextWithNumberView textWithNumberView = (TextWithNumberView) LayoutInflater.from(this).inflate(R.layout.v_tab_item, tabLayout, false);

        switch (i) {
            case 0:
                textWithNumberView.setText(R.string.warning);
                break;
            case 1:
                textWithNumberView.setText(R.string.notice);
                break;
            case 2:
                textWithNumberView.setText(R.string.messages);
                break;
            case 3:
                textWithNumberView.setText(R.string.reply);
                break;
        }
        return textWithNumberView;
    }

}
