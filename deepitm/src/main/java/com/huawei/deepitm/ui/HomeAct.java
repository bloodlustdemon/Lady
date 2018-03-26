package com.huawei.deepitm.ui;

import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.huawei.deepitm.BuildConfig;
import com.huawei.deepitm.R;
import com.huawei.deepitm.adapter.HomeAdapter;
import com.huawei.deepitm.bean.RegionBean;
import com.huawei.deepitm.bean.UnReadMsgBean;
import com.huawei.deepitm.helper.Constants;
import com.huawei.deepitm.helper.HomeObserver;
import com.huawei.deepitm.helper.HomeObserverable;
import org.paul.lib.base.BaseAct;
import org.paul.lib.base.BaseFrag;
import org.paul.lib.helper.SPHelper;
import org.paul.lib.manager.ThreadManager;

import java.util.*;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class HomeAct extends BaseAct implements HomeObserverable {
    @Override
    protected int getLayoutId() {
        return R.layout.act_home;
    }

    @Override
    protected void bindUi() {
        list = new ArrayList<>();
        ViewPager viewPager = $(R.id.vp_home);
        initVp(viewPager);
        TabLayout tabLayout = $(R.id.tab_home);
        tabLayout.setupWithViewPager(viewPager);
        initTab(tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        loadData();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UNREAD_FAILED:
                    break;
                case UNREAD_SUCCESS:
//FIXME                    notifyObserver(((UnReadMsgBean) msg.obj).getData().size());
                    notifyObserver(3);
                    break;
            }
        }
    };
    private final int UNREAD_SUCCESS = 0x11;
    private final int UNREAD_FAILED = 0x12;

    private void loadData() {
        ThreadManager.getThreadManager().submit(new Runnable() {
            @Override
            public void run() {
                UnReadMsgBean unReadMsgBean = netManager.post(BuildConfig.API_HOST + String.format(getString(R.string.unread_count_api),
                        BuildConfig.API_LV),createUnreadMsgParams(), UnReadMsgBean.class);
                if (null != unReadMsgBean) {
                    if (unReadMsgBean.getCode() == Constants.SUCCESS) {
                        handler.obtainMessage(UNREAD_SUCCESS, unReadMsgBean).sendToTarget();
                    } else {
                        handler.obtainMessage(UNREAD_FAILED, unReadMsgBean).sendToTarget();
                    }
                } else {
                    handler.sendEmptyMessage(UNREAD_FAILED);
                }
            }
        });

    }

    private Map<String,Object> createUnreadMsgParams() {
        Map<String, Object> ret = new HashMap<String, Object>();
//        ret.put("unifyID", );
//        ret.put("serviceType", "24");
        ret.put("notifyType", "2");
        ret.put("receiver", SPHelper.getString(HomeAct.this, "receiver", ""));
        ret.put("noticeStatus", "1");
        ret.put("readStatus", "0");
        return ret;
    }

    private void initVp(ViewPager viewPager) {
        ArrayList<Fragment> mList = new ArrayList<Fragment>();
        BaseFrag overViewFrag = new OverViewFrag();
        BaseFrag discoveryFrag = new DiscoveryFrag();
        BaseFrag solutionsFrag = new SolutionsFrag();
        BaseFrag accountFrag = new AccountFrag();
        registerObserver((OverViewFrag) overViewFrag);
        registerObserver((DiscoveryFrag) discoveryFrag);
        mList.add(overViewFrag);
        mList.add(discoveryFrag);
        mList.add(solutionsFrag);
        mList.add(accountFrag);
        HomeAdapter adapter = new HomeAdapter(getSupportFragmentManager(), mList, this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
    }

    private void initTab(TabLayout tabLayout) {
        tabLayout.getTabAt(0).setIcon(R.drawable.slct_overview).setText(R.string.overview);
        tabLayout.getTabAt(1).setIcon(R.drawable.slct_discovery).setText(R.string.discovery);
        tabLayout.getTabAt(2).setIcon(R.drawable.slct_solutions).setText(R.string.solutions);
        tabLayout.getTabAt(3).setIcon(R.drawable.slct_account).setText(R.string.account);
    }

    private List<HomeObserver> list;

    @Override
    public void registerObserver(HomeObserver o) {
        list.add(o);
    }

    @Override
    public void removeObserver(HomeObserver o) {
        if (!list.isEmpty())
            list.remove(o);
    }

    @Override
    public void notifyObserver(Integer num) {
        for (int i = 0; i < list.size(); i++) {
            HomeObserver oserver = list.get(i);
            oserver.update(num);
        }
    }
}
