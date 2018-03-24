package com.huawei.deepitm.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.huawei.deepitm.R;
import com.huawei.deepitm.adapter.SolutionsAdapter;
import com.huawei.deepitm.helper.GlideImageLoader;
import com.youth.banner.Banner;
import org.paul.lib.base.BaseFrag;

import java.util.ArrayList;
import java.util.List;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class SolutionsFrag extends BaseFrag {
    @Override
    public int getLayoutId() {
        return R.layout.frag_solutions;
    }

    @Override
    protected void bindUi() {
        Banner banner = $(R.id.banner);
        images = new ArrayList();
        images.add(R.drawable.banner1);
        images.add(R.drawable.banner2);
        images.add(R.drawable.banner3);
        images.add(R.drawable.banner4);
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();

        RecyclerView recyclerView = $(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new SolutionsAdapter(getContext()));
    }

    private List images;
}
