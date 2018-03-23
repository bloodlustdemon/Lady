package org.paul.lib.manager;

import android.util.Log;
import com.google.gson.Gson;
import org.paul.lib.base.BaseBean;
import org.paul.lib.base.Constants;
import org.paul.lib.helper.SSLHelper;

import javax.net.ssl.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.Map;

public class NetManager {
    private static final String TAG = NetManager.class.getSimpleName();
    private static final int READ_TIME = 4 * 1000;
    private static final int CONNECT_TIME = 4 * 1000;

    private static NetManager netManager = new NetManager();

    private NetManager() {
    }

    public static NetManager getNetManager() {
        return netManager;
    }

    public <T extends BaseBean> T post(String spec, Map<String, Object> params, Class<T> clz) {
        Log.d(TAG, "REQUEST URL :[" + spec + "]");
        try {
            HttpURLConnection connection = initHttp(spec, "POST", params);
            T t = handleResult(clz, connection);
            if (t != null) {
                Log.d(TAG, "REQUEST RET :" + t.toString());
                return t;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "ERROR IN NET EVENT, " + "error clz " + clz.getSimpleName());
        }
        return null;
    }

    public <T extends BaseBean> T get(String spec, Class<T> clz) {
        Log.d(TAG, "REQUEST URL :[" + spec + "]");
        try {
            HttpURLConnection connection = initHttp(spec, "GET", null);
            T t = handleResult(clz, connection);
            if (t != null) {
                Log.d(TAG, "REQUEST RET :" + t.toString());
                return t;
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "ERROR IN NET EVENT, " + "error clz " + clz.getSimpleName());
        }
        return null;
    }

    private <T extends BaseBean> T handleResult(Class<T> clz, HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            try {
                BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int len;
                byte[] arr = new byte[1024];
                while ((len = bis.read(arr)) != -1) {
                    bos.write(arr, 0, len);
                    bos.flush();
                }
                bos.close();
                String res = bos.toString("utf-8");
                Log.d(TAG, "res=" + res);
                return new Gson().fromJson(res, clz);
            } catch (com.google.gson.JsonSyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "ret code = " + responseCode);
        }
        return null;
    }

    private HttpURLConnection initHttp(String spec, String method, Map<String, Object> params) throws IOException {
        HttpURLConnection connection = null;
        URL url = new URL(spec);
        if (isHttps(spec)) {
            connection = (HttpsURLConnection) url.openConnection();
            trustAllHosts((HttpsURLConnection) connection);
        } else {
            connection = (HttpURLConnection) url.openConnection();
        }
        connection.setRequestMethod(method);
        connection.setConnectTimeout(CONNECT_TIME);
        connection.setReadTimeout(READ_TIME);
        connection.setUseCaches(false);
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36");
        connection.addRequestProperty("Content-Type", "application/json;charset=UTF-8");
        connection.addRequestProperty("Connection", "keep-alive");
        connection.addRequestProperty("beId", "101");
        connection.addRequestProperty("Accept-Language", "en-US");
        connection.addRequestProperty("token", Constants.getToken());
        connection.addRequestProperty("Accept", "application/json, text/plain, */*");
        initHeader(connection);
        initBody(connection, params);
        return connection;
    }

    private static String createParams(Map<String, Object> params) {
        return new Gson().toJson(params);
    }

    private HttpURLConnection initHeader(HttpURLConnection connection) {
        return connection;
    }

    private HttpURLConnection initBody(HttpURLConnection connection, Map<String, Object> params) throws IOException {
        if (null != params) {
            connection.setDoOutput(true);
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            printWriter.write(createParams(params));
            printWriter.flush();
        }
        return connection;
    }

    private void handleHeader() {
        return;
    }

    private void handleBody() {
        return;
    }

    /**
     * 判断是否HTTPS请求
     *
     * @param spec
     * @return
     */
    private boolean isHttps(String spec) {
        spec = spec.toLowerCase(Locale.CHINA);//默认中国
        return spec.startsWith("https");
    }

    private void trustAllHosts(HttpsURLConnection httpsURLConnection) {
        SSLSocketFactory oldSocketFactory = null;
        HostnameVerifier oldHostnameVerifier = null;
        oldSocketFactory = SSLHelper.trustAllHosts(httpsURLConnection);
        oldHostnameVerifier = httpsURLConnection.getHostnameVerifier();
        httpsURLConnection.setHostnameVerifier(SSLHelper.DO_NOT_VERIFY);
    }


}

