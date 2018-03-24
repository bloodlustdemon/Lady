package com.huawei.deepitm.widget.pullextend;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.huawei.deepitm.R;

import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * Created by Renny on 2018/1/24.
 */

public class ExtendHeadAdapter extends BGARecyclerViewAdapter<String> {
    public ExtendHeadAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_header);
    }
    @Override
    public void setItemChildListener(final BGAViewHolderHelper helper, int viewType) {
        helper.setItemChildClickListener(R.id.item_title);

    }

    @Override
    public void fillData(BGAViewHolderHelper helper, int position, String model) {
        TextView textView=helper.getView(R.id.item_title);
        textView.setText(model);
    }
}
