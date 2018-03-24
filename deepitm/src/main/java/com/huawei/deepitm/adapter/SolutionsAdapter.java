package com.huawei.deepitm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.deepitm.R;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class SolutionsAdapter extends RecyclerView.Adapter<SolutionsAdapter.VH> {

    private Context mContext;

    public SolutionsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH vh = null;
        vh = new VH(LayoutInflater.from(mContext).inflate(R.layout.item_discovery, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class VH extends RecyclerView.ViewHolder {

        public VH(View itemView) {
            super(itemView);
        }
    }
}
