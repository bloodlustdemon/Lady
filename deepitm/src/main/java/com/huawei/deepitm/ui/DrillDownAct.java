package com.huawei.deepitm.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.gson.Gson;
import com.huawei.deepitm.R;
import com.huawei.deepitm.bean.DataT;
import com.huawei.deepitm.widget.GanttChart;
import org.paul.lib.base.BaseAct;

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
        ViewStub viewStub = $(R.id.vs);
        ViewStub viewStub2 = $(R.id.vs_2);
        View inflate = viewStub.inflate();
        View inflate2 = viewStub2.inflate();
        GanttChart ganttChart = (GanttChart) inflate.findViewById(R.id.gantt);
        ganttChart.setData(initData().getDownLinkUtilizations());
    }
    private DataT initData() {
        String spec = "{\n" +
                "    \"downLinkUtilizations\": {\n" +
                "        \"1515772800000\": 0.4934,\n" +
                "        \"1515774600000\": 0.6485,\n" +
                "        \"1515776400000\": 0.6107,\n" +
                "        \"1515778200000\": 0.3337,\n" +
                "        \"1515780000000\": 0.4658,\n" +
                "        \"1515781800000\": 0.1152,\n" +
                "        \"1515783600000\": 0.342,\n" +
                "        \"1515785400000\": 0.566,\n" +
                "        \"1515787200000\": 0.5991,\n" +
                "        \"1515789000000\": 0.0407,\n" +
                "        \"1515790800000\": 0.2711,\n" +
                "        \"1515792600000\": 0.8244,\n" +
                "        \"1515794400000\": 0.0567,\n" +
                "        \"1515796200000\": 0.4328,\n" +
                "        \"1515798000000\": 0.5643,\n" +
                "        \"1515799800000\": 0.1244,\n" +
                "        \"1515801600000\": 0.6564,\n" +
                "        \"1515803400000\": 0.9684,\n" +
                "        \"1515805200000\": 0.5704,\n" +
                "        \"1515807000000\": 0.4354,\n" +
                "        \"1515808800000\": 0.163,\n" +
                "        \"1515810600000\": 0.4511,\n" +
                "        \"1515812400000\": 0.1425,\n" +
                "        \"1515814200000\": 0.1643,\n" +
                "        \"1515816000000\": 0.5164,\n" +
                "        \"1515817800000\": 0.3384,\n" +
                "        \"1515819600000\": 0.6808,\n" +
                "        \"1515821400000\": 0.2773,\n" +
                "        \"1515823200000\": 0.6308,\n" +
                "        \"1515825000000\": 0.5395,\n" +
                "        \"1515826800000\": 0.6601,\n" +
                "        \"1515828600000\": 0.3857,\n" +
                "        \"1515830400000\": 0.6529,\n" +
                "        \"1515832200000\": 0.9328,\n" +
                "        \"1515834000000\": 0.653,\n" +
                "        \"1515835800000\": 0.3695,\n" +
                "        \"1515837600000\": 0.8149,\n" +
                "        \"1515839400000\": 0.3013,\n" +
                "        \"1515841200000\": 0.11,\n" +
                "        \"1515843000000\": 0.3624,\n" +
                "        \"1515844800000\": 0.2706,\n" +
                "        \"1515846600000\": 0.6358,\n" +
                "        \"1515848400000\": 0.4094,\n" +
                "        \"1515850200000\": 0.1733,\n" +
                "        \"1515852000000\": 0.5488,\n" +
                "        \"1515853800000\": 0.0058,\n" +
                "        \"1515855600000\": 0.9371\n" +
                "    }\n" +
                "}";
        Gson gson = new Gson();
        return gson.fromJson(spec, DataT.class);
    }

    private GoogleMap mMap;
    private MapView mMapView;
    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";

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
