package com.huawei.deepitm.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.huawei.deepitm.R;
import com.huawei.deepitm.bean.BandlinesBean;
import com.huawei.deepitm.bean.BroadBandLinePerformance;
import com.huawei.deepitm.widget.TopBar;
import com.huawei.deepitm.widget.TrendCircle24H;
import com.huawei.deepitm.widget.pullextend.ExtendListHeader;
import com.huawei.deepitm.widget.pullextend.PullExtendLayoutForRecyclerView;
import org.paul.lib.base.BaseFrag;
import org.paul.lib.widget.TextWithNumberView;

import java.util.ArrayList;
import java.util.List;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class OverViewFrag extends BaseFrag {
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
        TopBar topBar = $(R.id.topbar_overview);
        topBar.setNum(3);
        topBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAct(NoticeAct.class);
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
                holder.setListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null == bandlinesBean) {
                            return;
                        }
                        BroadBandLinePerformance broadBandLinePerformance = bandlinesBean.getData().getBroadBandLinePerformance();/*.getLineId()*/
                        if (null == broadBandLinePerformance) {
                            return;
                        }
//                        Bundle bundle = new Bundle();
//                        bundle.putString("lineId", bandlinesBean.getData().getBroadBandLinePerformance().getLineId());
//                        startWithData(CardDetAct.class, bundle);
                    }
                });
                TextWithNumberView textWithNumberView = (TextWithNumberView) holder.itemView.findViewById(R.id.tv_card_up);
                TextView textViewDn = (TextView) holder.itemView.findViewById(R.id.tv_card_dn);
                TrendCircle24H trendCircle24H = (TrendCircle24H) holder.itemView.findViewById(R.id.content);
                if (null != bandlinesBean && null != bandlinesBean.getData().getBroadBandLinePerformance()) {
                    trendCircle24H.setData(bandlinesBean.getData().getBroadBandLinePerformance().getDownLinkUtilizations());
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
}
