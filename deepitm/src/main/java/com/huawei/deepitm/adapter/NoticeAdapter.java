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
public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.VHNotice> {

    private final Context mContext;

    public NoticeAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public VHNotice onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VHNotice vhNotice = new VHNotice(
                LayoutInflater.from(mContext).inflate(R.layout.item_notice,parent,false)
        );
        return vhNotice;
    }

    @Override
    public void onBindViewHolder(@NonNull VHNotice holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class VHNotice extends RecyclerView.ViewHolder {

        public VHNotice(View itemView) {
            super(itemView);
        }
    }

}
