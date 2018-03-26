package com.huawei.deepitm.ui;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.huawei.deepitm.R;
import com.huawei.deepitm.adapter.DiscoveryAdapter;
import com.huawei.deepitm.helper.HomeObserver;
import com.huawei.deepitm.widget.TopBar;
import org.paul.lib.base.BaseFrag;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class DiscoveryFrag extends BaseFrag implements HomeObserver {
    @Override
    public int getLayoutId() {
        return R.layout.frag_discovery;
    }

    @Override
    protected void bindUi() {

        topBar = $(R.id.topbar_discovery);
        topBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(NoticeAct.class);
            }
        });

        topBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TabLayout tabLayout = $(R.id.tab);
        RecyclerView recyclerView = $(R.id.recycle);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabTextColors(Color.LTGRAY, Color.BLACK);
        String[] stringArray = getResources().getStringArray(R.array.discovery);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DiscoveryAdapter disAdapter = new DiscoveryAdapter(getContext());
        recyclerView.setAdapter(disAdapter);

        for (int i = 0; i < stringArray.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(stringArray[i]));
        }
        linearLayout = $(R.id.linear);
        TextView tv = $(R.id.tv);
        $(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popFollow();
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.getText().equals("Industry") || tab.getText().equals("Products")) {
//                    linearLayout.setVisibility(View.VISIBLE);
//                } else {
//                    linearLayout.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void popFollow() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        PopupWindow instance = new PopupWindow(LayoutInflater.from(getContext()).inflate(R.layout.pop_follow, null),
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        instance.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1.0f;
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                getActivity().getWindow().setAttributes(lp);
            }
        });
        View view = instance.getContentView();
        initPop(view);
        instance.setAnimationStyle(R.style.animpop);
        instance.showAsDropDown(linearLayout, 0, -linearLayout.getHeight() - 10);
    }

    private void initPop(View view) {

        pops = getResources().getStringArray(R.array.pop);
        TextView tv1 = $(view, R.id.item_1);
        TextView tv2 = $(view, R.id.item_2);
        TextView tv3 = $(view, R.id.item_3);
        TextView tv4 = $(view, R.id.item_4);
        TextView tv5 = $(view, R.id.item_5);
        TextView tv6 = $(view, R.id.item_6);
        TextView tv7 = $(view, R.id.item_7);
        TextView tv8 = $(view, R.id.item_8);
        TextView tv9 = $(view, R.id.item_9);

        tv1.setOnClickListener(listener);
        tv2.setOnClickListener(listener);
        tv3.setOnClickListener(listener);
        tv4.setOnClickListener(listener);
        tv5.setOnClickListener(listener);
        tv6.setOnClickListener(listener);
        tv7.setOnClickListener(listener);
        tv8.setOnClickListener(listener);
        tv9.setOnClickListener(listener);

    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v instanceof TextView) {
                ((TextView) v).setSelected(!((TextView) v).isSelected());
            }
        }
    };
    private String[] pops;
    private LinearLayout linearLayout;
    private TopBar topBar;
    private final int UNREADMSG_COUNT = 0x23;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UNREADMSG_COUNT:
                    topBar.setNum((Integer) msg.obj);
                    break;
            }
        }
    };

    @Override
    public void update(int num) {
        handler.obtainMessage(UNREADMSG_COUNT, num).sendToTarget();
    }

}
