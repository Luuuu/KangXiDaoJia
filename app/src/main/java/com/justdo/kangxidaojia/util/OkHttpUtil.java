package com.justdo.kangxidaojia.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @创建者 administrator
 * @创建时间 2017/5/21 19:49
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 2017/5/21$
 * @更新描述 ${TODO}
 */

public class OkHttpUtil<T> {
    private  Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mPushData != null) {
                mPushData.onPushData(mClazzBean);
            }
        }
    };
    public         T        mClazzBean;
    private static PushData mPushData;

    public OkHttpUtil  netGetData(String url , final Class<T> clazz) {
        Log.d("adBean", "adBean url "+ url);
        OkHttpClient okHttpClient =   new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String bean = response.body().string();
                    mClazzBean = (T) GsonUtil.toBean(bean, clazz);
                //    Log.d("adBean", "adBean "+ mClazzBean.toString());
                    mHandler.sendEmptyMessage(0);
                }
            }
        });
        return OkHttpUtil.this;
    }
    public interface PushData<T>{
       void onPushData(T t);
    }
    public void setOnPushData(PushData pushData){
        mPushData = pushData;
    }
}
