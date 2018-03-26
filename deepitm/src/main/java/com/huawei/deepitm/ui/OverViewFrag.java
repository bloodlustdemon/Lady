package com.huawei.deepitm.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.deepitm.BuildConfig;
import com.huawei.deepitm.R;
import com.huawei.deepitm.bean.BandlinesBean;
import com.huawei.deepitm.bean.BroadBandLinePerformance;
import com.huawei.deepitm.helper.Constants;
import com.huawei.deepitm.helper.HomeObserver;
import com.huawei.deepitm.widget.TopBar;
import com.huawei.deepitm.widget.TrendCircle24H;
import com.huawei.deepitm.widget.pullextend.ExtendListHeader;
import com.huawei.deepitm.widget.pullextend.PullExtendLayoutForRecyclerView;
import org.paul.lib.base.BaseFrag;
import org.paul.lib.helper.SPHelper;
import org.paul.lib.manager.ThreadManager;
import org.paul.lib.widget.TextWithNumberView;

import java.util.ArrayList;
import java.util.List;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class OverViewFrag extends BaseFrag implements HomeObserver {
    @Override
    public int getLayoutId() {
        return R.layout.frag_overview;
    }

    @Override
    protected void bindUi() {
        PullExtendLayoutForRecyclerView pullExtendLayoutForRecyclerView = (PullExtendLayoutForRecyclerView) $(R.id.pull_extend);
        pullExtendLayoutForRecyclerView.setPullLoadEnabled(false);

        mPullNewHeader = (ExtendListHeader) $(R.id.extend_header);
        listHeader = mPullNewHeader.getRecyclerView();
        listHeader.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        listHeader.setAdapter(new HeaderAdpater());
        mRecyclerView = (RecyclerView) $(R.id.over_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        overViewAdapter = new OverViewAdapter();
        mRecyclerView.setAdapter(overViewAdapter);
        topBar = $(R.id.topbar_overview);
        topBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(NoticeAct.class);
            }
        });

        loadData();
    }

    private void loadData() {
        ThreadManager.getThreadManager().submit(new Runnable() {
            @Override
            public void run() {
                BandlinesBean band = netManager.get(BuildConfig.API_HOST + String.format(getString(R.string.alram_broadbandlines_api),
                        BuildConfig.API_LV, SPHelper.getString(getContext(), "customerId", "")),
                        BandlinesBean.class);
                if (null != band) {
                    if (band.getCode() == Constants.SUCCESS) {
                        handler.obtainMessage(BAND_LINES_SUCCESS, band).sendToTarget();
                    } else {
                        handler.obtainMessage(BAND_LINES_FAILED, band).sendToTarget();
                    }
                } else {
                    handler.sendEmptyMessage(BAND_LINES_FAILED);
                }
            }
        });
    }

    private ExtendListHeader mPullNewHeader;
    private RecyclerView listHeader;
    private List<String> mDatas = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private OverViewAdapter overViewAdapter;

    public class OverViewAdapter extends RecyclerView.Adapter<VH> {

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            VH vh = null;
            if (viewType == 0) {
                vh = new VH(LayoutInflater.from(getContext()).inflate(R.layout.card_trendcircle, parent, false));
            } else if (viewType == 1) {
                vh = new VH(LayoutInflater.from(getContext()).inflate(R.layout.card_trend24h, parent, false));
            } else if (viewType == 2) {
                vh = new VH(LayoutInflater.from(getContext()).inflate(R.layout.card_trendcircle, parent, false));
            }
            return vh;
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
            if (position == 1) {
                if(null==bandlinesBean){
                    return;
                }
                final BandlinesBean.Data data = bandlinesBean.getData();
                holder.setListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("issue", null != data ? data.getIssue() : 0);
                        startWithData(DrillDownAct.class, bundle);
                        //    startAct(DrillDownAct.class);
                    }
                });
                TextWithNumberView textWithNumberView = (TextWithNumberView) holder.itemView.findViewById(R.id.tv_card_up);
                TextView textViewDn = (TextView) holder.itemView.findViewById(R.id.tv_card_dn);
                TrendCircle24H trendCircle24H = (TrendCircle24H) holder.itemView.findViewById(R.id.content);
                if (null == data) {
                    return;
                }
                BroadBandLinePerformance broadBandLinePerformance = data.getBroadBandLinePerformance();
                if (null == broadBandLinePerformance) {
                    trendCircle24H.setData(null,
                            data.getLineCount(), data.getIssue());
                    return;
                } else {
                    trendCircle24H.setData(data.getBroadBandLinePerformance().getDownLinkUtilizations(),
                            data.getLineCount(), data.getIssue());

                }

            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        public void update(BandlinesBean bean) {

            bandlinesBean = bean;
            notifyDataSetChanged();
        }

        private BandlinesBean bandlinesBean;
    }

    public class VH extends RecyclerView.ViewHolder {

        private View itemView;

        public void setListener(View.OnClickListener listener) {
            if (null != listener) {
                itemView.setOnClickListener(listener);
            }
        }

        public VH(View itemView) {
            super(itemView);
            VH.this.itemView = itemView;
        }
    }

    public class HeaderAdpater extends RecyclerView.Adapter<VH> {

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            VH vh = null;
            if (viewType == 0) {
                vh = new VH(LayoutInflater.from(getContext()).inflate(R.layout.item_call, parent, false));
                return vh;
            } else if (viewType == 1) {
                vh = new VH(LayoutInflater.from(getContext()).inflate(R.layout.item_collection, parent, false));
                return vh;
            } else if (viewType == 2) {
                vh = new VH(LayoutInflater.from(getContext()).inflate(R.layout.item_quickaction, parent, false));

                return vh;
            }
            return vh;
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }
    }

    private TopBar topBar;
    private final int UNREADMSG_COUNT = 0x23;
    private final int BAND_LINES_SUCCESS = 0x13;
    private final int BAND_LINES_FAILED = 0x14;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UNREADMSG_COUNT:
                    topBar.setNum((Integer) msg.obj);
                    break;
                case BAND_LINES_SUCCESS:
                    updateBandLine((BandlinesBean) msg.obj);
                    break;
                case BAND_LINES_FAILED:
                    if (null != msg.obj) {
                        Toast.makeText(getContext(), ((BandlinesBean) msg.obj).getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), R.string.net_error, Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void updateBandLine(BandlinesBean bean) {
        overViewAdapter.update(bean);
    }

    @Override
    public void update(int num) {
        handler.obtainMessage(UNREADMSG_COUNT, num).sendToTarget();
    }

}
