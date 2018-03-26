package com.huawei.deepitm.ui;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.gson.Gson;
import com.huawei.deepitm.BuildConfig;
import com.huawei.deepitm.R;
import com.huawei.deepitm.bean.BroadBandLine;
import com.huawei.deepitm.bean.BroadBandLineStatusBean;
import com.huawei.deepitm.bean.DataT;
import com.huawei.deepitm.widget.GanttChart;
import com.huawei.deepitm.widget.TopBar;
import org.paul.lib.base.BaseAct;
import org.paul.lib.base.Constants;
import org.paul.lib.helper.SPHelper;
import org.paul.lib.manager.ThreadManager;

import java.util.List;

/**
 * AUTHOR Paul
 * DATE 2018/3/25
 */
public class DrillDownAct extends BaseAct implements OnMapReadyCallback {
    @Override
    protected int getLayoutId() {
        return R.layout.act_drilldown;
    }

    @Override
    protected void bindUi() {
        mMapView = $(R.id.mapview);
        Bundle mapBundle = null;
        if (null != savedInstanceState) {
            mapBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView.onCreate(mapBundle);
        mMapView.getMapAsync(this);

        int issueCount = parentIntent.getExtras().getInt("issue", 0);
        if (issueCount >= 0) {
            ViewStub viewStub = $(R.id.vs);
            ViewStub viewStub2 = $(R.id.vs_2);
            View inflate = viewStub.inflate();
            View inflate2 = viewStub2.inflate();
            ganttChart = (GanttChart) inflate.findViewById(R.id.gantt);
        }
        topBar = $(R.id.topbar_drilldown);
        topBar.setTitle(getString(R.string.title_drilldown));
        topBar.setTitleColor(Color.WHITE);

        loadData();

    }

    private void loadData() {
        ThreadManager.getThreadManager().submit(new Runnable() {
            @Override
            public void run() {
                BroadBandLineStatusBean bean = netManager.get(BuildConfig.API_HOST + String.format(getString(R.string.all_broadbandlines_api),
                        BuildConfig.API_LV, SPHelper.getString(DrillDownAct.this, "customerId", "")), BroadBandLineStatusBean.class);
                if (null != bean) {
                    if (bean.getCode() == com.huawei.deepitm.helper.Constants.SUCCESS) {
                        handler.obtainMessage(BROAD_SUCCESS, bean).sendToTarget();
                    } else {
                        handler.obtainMessage(BROAD_FAILED, bean).sendToTarget();
                    }
                } else {
                    handler.sendEmptyMessage(BROAD_FAILED);
                }
            }
        });
    }

    private void loadIssueLine() {

    }

    private TopBar topBar;
    private GanttChart ganttChart;
    private GoogleMap mMap;
    private MapView mMapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private final int BROAD_SUCCESS = 0x11;
    private final int BROAD_FAILED = 0x12;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case BROAD_SUCCESS:
                    initMap((BroadBandLineStatusBean) msg.obj);
                    break;
                case BROAD_FAILED:
                    break;
            }
        }
    };

    private void initMap(BroadBandLineStatusBean bean) {
        BroadBandLineStatusBean.Data data = bean.getData();
        List<BroadBandLine> broadBandLineStatuss = data.getBroadBandLineStatuss();
        for (BroadBandLine broadBandLine : broadBandLineStatuss) {
            //maker at map
            final BroadBandLine.Data dataInner = broadBandLine.getBroadBandLine();
            String lineCoordinate = dataInner.getLineCoordinate();
            String[] split = lineCoordinate.split(",");
            LatLng BRISBANE = new LatLng(Float.valueOf(split[1]), Float.valueOf(split[0]));
            mMap.addMarker(new MarkerOptions()
                    .position(BRISBANE)
//                    .title("Brisbane")
//                    .snippet("Population: 2,074,200")
                    .icon(BitmapDescriptorFactory.fromResource(
                            broadBandLine.getStatus() == 1 ?
                                    R.drawable.ic_green_maker
                                    : R.drawable.ic_red_maker)));
        }
    }

    private void initGant(BroadBandLineStatusBean bean) {
//        ganttChart.setData();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng hk = new LatLng(22.2988123, 114.1721746);
        CameraPosition build = new CameraPosition.Builder().target(hk)
                .zoom(13f)
                .bearing(0)
                .tilt(0)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(build));
        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }
    }

    private void changeCamera(CameraUpdate update/*, GoogleMap.CancelableCallback callback*/) {
        mMap.moveCamera(update);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
