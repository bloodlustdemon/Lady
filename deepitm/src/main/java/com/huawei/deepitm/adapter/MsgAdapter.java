package com.huawei.deepitm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.deepitm.R;
import org.paul.lib.widget.RoundImage;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.VHMsg> {

    private final Context mContext;

    public MsgAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public VHMsg onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VHMsg vhMsg = new VHMsg(
                LayoutInflater.from(mContext).inflate(R.layout.item_msg, parent, false)
        );
        return vhMsg;
    }

    @Override
    public void onBindViewHolder(@NonNull VHMsg holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class VHMsg extends RecyclerView.ViewHolder {

        private RoundImage image;

        public VHMsg(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_head);
        }
    }

}
