package com.justdo.kangxidaojia.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.justdo.kangxidaojia.R;
import com.justdo.kangxidaojia.bean.ADBean;
import com.justdo.kangxidaojia.bean.UpdateBean;
import com.justdo.kangxidaojia.util.Constants;
import com.justdo.kangxidaojia.util.GsonUtil;
import com.justdo.kangxidaojia.util.HttpUtil;
import com.justdo.kangxidaojia.util.MacUtil;
import com.justdo.kangxidaojia.util.OkHttpUtil;
import com.justdo.kangxidaojia.util.PackageUtil;
import com.justdo.kangxidaojia.util.SpUtil;
import com.justdo.kangxidaojia.util.UrlUtil;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.justdo.kangxidaojia.R.id.shop_id;
import static com.justdo.kangxidaojia.util.Constants.KEY_SHOP_ID;

public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_INSTALL = 100;
    private TextView mTimer;
    private int t = 6;
    private LinearLayout mLl_ad;
    private ImageView    mIv_ad;
    private LinearLayout mLl_visible_ad;
    private ADBean       mAdBean;
    private Handler mHandler = new Handler();
    private FrameLayout    mShop_id;
    private EditText       mEt_shop_id;
    private Button         mBt_shop_id;
    private boolean        mBingShopID;
    private ProgressDialog progressDialog;
    private UpdateBean     mUpdateBean;
    private int bindKey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d("splashActivity", "splashActivityOnCreate");
        new SpUtil(this);
        initView();
        autoUpdate();
    }

    @Override
    protected void onDestroy() {
        Log.d("splashActivity", "splashActivityonDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void bindShowID() {
        mShop_id.setVisibility(View.VISIBLE);
        mEt_shop_id.requestFocus();
        mBt_shop_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable text = mEt_shop_id.getText();
                String str = text.toString();
                if (str.isEmpty()) {
                    return;
                } else {
                    SpUtil.saveString("SHOP_ID", str);
                    Constants.SID = str;
                    isBingShopID();
                }

            }
        });
    }

    private void initData() {
        mShop_id.setVisibility(View.GONE);
        mTimer.setVisibility(View.VISIBLE);
        mTimer.setText("" + t);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                t--;
                mTimer.setText("" + t);
                if (t == 3) {
                    mLl_visible_ad.setVisibility(View.INVISIBLE);
                    mLl_ad.setVisibility(View.VISIBLE);
                    mTimer.setText("" + t);
                }
                if (t != 0) {
                    mHandler.postDelayed(this, 1000);
                }
                if (t == 0) {
                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 1000);
        netData();
    }

    public void netData() {
        String format;
        Constants.MID = MacUtil.getMacAddress(SplashActivity.this);
        format = String.format(UrlUtil.AD_URL, Constants.SID, 0, Constants.MID);
    }

    private void initView() {
        mEt_shop_id = (EditText) findViewById(R.id.et_shop_id);
        mBt_shop_id = (Button) findViewById(R.id.bt_shop_id);
        mTimer = (TextView) findViewById(R.id.splash_timer);
        mLl_ad = (LinearLayout) findViewById(R.id.ll_ad);
        mIv_ad = (ImageView) findViewById(R.id.iv_ad);
        mLl_visible_ad = (LinearLayout) findViewById(R.id.ll_visible_ad);
        mShop_id = (FrameLayout) findViewById(shop_id);
    }


    public void isBingShopID() {
        boolean isBind = false;
        Constants.MID = MacUtil.getMacAddress(SplashActivity.this);
        String format ;
        if (Constants.SID != null && Integer.parseInt(Constants.SID) != -1) {
            format = String.format(UrlUtil.AD_URL, Constants.SID, 0, Constants.MID);
        } else {
            format = String.format(UrlUtil.AD_URL_TOW, 0, Constants.MID);
        }

        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.netGetData(format, ADBean.class).setOnPushData(new OkHttpUtil.PushData<ADBean>() {
            @Override
            public void onPushData(ADBean adBean) {
                if ( adBean.getStatus() == 400) {
                    t = 3;
                    bindKey++;
                    if (bindKey > 1) {
                        bindKey = 1;
                        Toast.makeText(SplashActivity.this.getApplicationContext(), "请输入正确的SHOP ID", Toast.LENGTH_LONG).show();
                    }
                    bindShowID();
                } else {
                    if (adBean != null && adBean.getData() != null && adBean.getData().size() != 0) {
                        Constants.SID = adBean.getData().get(0).getStoreId();
                        Glide.with(SplashActivity.this).load(UrlUtil.BASE_PIC_URL + adBean.getData().get(0).getThumbnailUrl()).into(mIv_ad);
                    } else {
                        t = 3;
                    }
                    initData();
                }

            }
        });

    }


//自动更新

    private void autoUpdate() {
        new Thread() {
            public void run() {
                int versionCode = PackageUtil.getVersionCode(
                        SplashActivity.this, getPackageName());
                Log.d("versionCode", "versionCode" + versionCode);
                OkHttpClient okHttpClient_get = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.SECONDS).build();
                String url = String.format(UrlUtil.UP_DATA, versionCode);
                Request request = new Request.Builder().get()
                        .url(url)
                        .build();
                Response response;
                try {
                    response = okHttpClient_get.newCall(request).execute();
                    String json = response.body().string();
                    mUpdateBean = GsonUtil.toBean(json, UpdateBean.class);
                    if (mUpdateBean.getStatus() != 400 && Float.parseFloat(mUpdateBean.getData().getVer()) > versionCode) {

                        initUpdateDialog(mUpdateBean);

                    } else {
                        enterHome();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    enterHome();
                }
            }
        }.start();
    }

    private void enterHome() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!KEY_SHOP_ID) {
                    Log.d("KEY_SHOP_ID", "KEY_SHOP_ID :" + KEY_SHOP_ID);
                    isBingShopID();
                } else {
                    Log.d("KEY_SHOP_ID", "KEY_SHOP_ID :" + KEY_SHOP_ID);
                    KEY_SHOP_ID = false;
                    bindKey = 1;
                    bindShowID();
                }
            }
        }, 0);
    }

    private void initUpdateDialog(final UpdateBean bean) {
        runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
                builder.setTitle("版本更新提示");
                builder.setMessage(bean.getMsg());
                builder.setNegativeButton("稍后再说", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        enterHome();
                    }
                });
                builder.setPositiveButton("立刻升级", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        progressDialog = new ProgressDialog(SplashActivity.this);
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.setCancelable(false);
                        progressDialog.show();
                        downLoadApk(bean);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.show();
            }
        });
    }

    private void downLoadApk(UpdateBean bean) {
        new Thread(new DownLoadTask(bean)) {
        }.start();
    }

    private class DownLoadTask implements Runnable {
        private UpdateBean mBean;
        public DownLoadTask(UpdateBean bean) {
            this.mBean = bean;
        }

        @Override
        public void run() {
            InputStream inputStream = null;
            FileOutputStream fos = null;
            try {
                Response response = HttpUtil.httpGet(mBean.getData().getLink());
                inputStream = response.body().byteStream();
                long max = response.body().contentLength();
                progressDialog.setMax((int) max);

                File apkFile = new File(Environment.getExternalStorageDirectory(), "kxdj.apk");
                fos = new FileOutputStream(apkFile);

                byte[] buffer = new byte[1024];
                int len = -1;
                int progress = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    progress += len;
                    progressDialog.setProgress(progress);
                }
                fos.flush();
                progressDialog.dismiss();
                installApk(apkFile);

            } catch (IOException e) {
                e.printStackTrace();
                enterHome();
            } finally {
                closeIo2(inputStream, fos);
            }
        }
    }

    public void installApk(File apkFile) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        Uri uri = Uri.fromFile(apkFile);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivityForResult(intent, REQUEST_INSTALL);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_INSTALL) {
            if (resultCode == RESULT_OK) {
            } else {
                enterHome();
            }
        }
    }


    private void closeIo2(Closeable... io) {
        if (io != null) {
            for (int i = 0; i < io.length; i++) {
                Closeable closeable = io[i];
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d("onKeyDownevent", "onKeyDownevent" + event.getKeyCode());
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mHandler.removeCallbacksAndMessages(null);
        }
        return super.onKeyDown(keyCode, event);
    }
}
