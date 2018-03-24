package com.huawei.deepitm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.deepitm.R;

import java.util.Random;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class DiscoveryAdapter extends RecyclerView.Adapter<DiscoveryAdapter.VH> {

    private final Context mContext;
    public DiscoveryAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH vh = null;
        View v;
        if (viewType == 1) {
            v = LayoutInflater.from(mContext).inflate(R.layout.item_discovery, parent, false);
        } else {
            v = LayoutInflater.from(mContext).inflate(R.layout.item_discovery_1, parent, false);
        }
        vh = new VH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return new Random().nextInt(1);
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class VH extends RecyclerView.ViewHolder {

        public VH(View itemView) {
            super(itemView);
        }
    }
}
