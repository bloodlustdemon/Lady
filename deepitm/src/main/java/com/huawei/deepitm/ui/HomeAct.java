package com.huawei.deepitm.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.huawei.deepitm.R;
import com.huawei.deepitm.adapter.HomeAdapter;
import org.paul.lib.base.BaseAct;
import org.paul.lib.base.BaseFrag;

import java.util.ArrayList;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class HomeAct extends BaseAct /*implements TabLayout.OnTabSelectedListener*/ {
    @Override
    protected int getLayoutId() {
        return R.layout.act_home;
    }

    @Override
    protected void bindUi() {

        ViewPager viewPager = $(R.id.vp_home);
        initVp(viewPager);
        TabLayout tabLayout = $(R.id.tab_home);
        tabLayout.setupWithViewPager(viewPager);
        initTab(tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private void initVp(ViewPager viewPager) {
        ArrayList<Fragment> mList = new ArrayList<Fragment>();
        BaseFrag overViewFrag = new OverViewFrag();
        BaseFrag discoveryFrag = new DiscoveryFrag();
        BaseFrag solutionsFrag = new SolutionsFrag();
        BaseFrag accountFrag = new AccountFrag();
        mList.add(overViewFrag);
        mList.add(discoveryFrag);
        mList.add(solutionsFrag);
        mList.add(accountFrag);
        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager(), mList, this);
        viewPager.setAdapter(adapter);
    }

    private void initTab(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.drawable.slct_overview).setText(R.string.overview);
        tabLayout.getTabAt(1).setIcon(R.drawable.slct_discovery).setText(R.string.discovery);
        tabLayout.getTabAt(2).setIcon(R.drawable.slct_solutions).setText(R.string.solutions);
        tabLayout.getTabAt(3).setIcon(R.drawable.slct_account).setText(R.string.account);
    }

}
