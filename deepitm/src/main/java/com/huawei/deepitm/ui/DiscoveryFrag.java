package com.huawei.deepitm.ui;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huawei.deepitm.R;
import com.huawei.deepitm.adapter.DiscoveryAdapter;
import com.huawei.deepitm.widget.TopBar;
import org.paul.lib.base.BaseFrag;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class DiscoveryFrag extends BaseFrag {
    @Override
    public int getLayoutId() {
        return R.layout.frag_discovery;
    }

    @Override
    protected void bindUi() {

        TopBar topBar = $(R.id.topbar_discovery);
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
//        topBar.setNum(arguments.getInt("msgCount",0));
//        Toolbar toolbar =$(R.id.toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startAct(NoticeAct.class);
//            }
//        });
        TabLayout tabLayout = $(R.id.tab);
        RecyclerView recyclerView = $(R.id.recycle);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabTextColors(Color.LTGRAY, Color.BLACK);
        String[] stringArray = getResources().getStringArray(R.array.discovery);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DiscoveryAdapter disAdapter = new DiscoveryAdapter(getContext());

//        disAdapter.data = new Integer[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        recyclerView.setAdapter(disAdapter);

        for (int i = 0; i < stringArray.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(stringArray[i]));
        }
        linearLayout = $(R.id.linear);
        TextView tv = $(R.id.tv);
        /*ImageView img=*/
        $(R.id.img).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                alertPop();
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getText().equals("Industry") || tab.getText().equals("Products")) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private LinearLayout linearLayout;
}
