package com.justdo.kangxidaojia.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.justdo.kangxidaojia.R;
import com.justdo.kangxidaojia.bean.DetailBean;
import com.justdo.kangxidaojia.util.Constants;
import com.justdo.kangxidaojia.util.OkHttpUtil;
import com.justdo.kangxidaojia.util.QrUtil;
import com.justdo.kangxidaojia.util.UrlUtil;

public class DetailsActivity extends AppCompatActivity {
    public static final int DEFAULT_STORAGE_SID = -1;
    private DetailBean mDetailBean;
    private TextView   mDetail_time;
    private TextView   mDetail_name;
    private TextView   mDetail_price;
    private TextView   mDetail_oneword;
    private TextView   mDetail_address;
    private TextView   mDetail_phone;
    private ImageView  mDetail_qr;
    private ImageView  mDetail_pic;
    private TextView   mViewById;
    private String     mItemID;
    private Bitmap mQrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        mItemID = intent.getStringExtra("ItemID");
        initView();
        initData();
    }

    private void initData() {
        final String format;
        if (Integer.parseInt(Constants.SID) != DEFAULT_STORAGE_SID) {
            format = String.format(UrlUtil.DETAIL_URL, mItemID, Constants.SID, Constants.MID);
        } else {
            format = String.format(UrlUtil.DETAIL_URL_TOW, mItemID, Constants.MID);
        }
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.netGetData(format, DetailBean.class).setOnPushData(new OkHttpUtil.PushData<DetailBean>() {
            @Override
            public void onPushData(DetailBean detailBean) {
                mDetailBean = detailBean;
                Log.e("mDetailBean",mDetailBean.getData().getProName());
                setlayout();
            }
        });
    }

    private void setlayout() {
        mQrImage = QrUtil.createQRImage(mDetailBean.getData().getQrcodeUrl());
        mDetail_name.setText(mDetailBean.getData().getProName());
        mDetail_price.setText("￥" + mDetailBean.getData().getDiscPrice());
        mDetail_oneword.setText(mDetailBean.getData().getOneword());
        mDetail_qr.setImageBitmap(mQrImage);
        mDetail_time.setText(mDetailBean.getData().getTimeLength() + "分钟");
        mDetail_address.setText("【地址】"+mDetailBean.getData().getAddress());
        mDetail_phone.setText(mDetailBean.getData().getTelphone());
        Glide.with(this).load(UrlUtil.BASE_PIC_URL + mDetailBean.getData().getImg1Url()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mDetail_pic);
    }

    private void initView() {
        mDetail_time = (TextView) findViewById(R.id.detail_time);
        mDetail_name = (TextView) findViewById(R.id.detail_name);
        mDetail_price = (TextView) findViewById(R.id.detail_Price);
        mDetail_oneword = (TextView) findViewById(R.id.detail_oneword);
        mDetail_address = (TextView) findViewById(R.id.detail_address);
        mDetail_phone = (TextView) findViewById(R.id.detail_phone);
        mDetail_qr = (ImageView) findViewById(R.id.detail_qr);
        mDetail_pic = (ImageView) findViewById(R.id.detail_pic);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mQrImage != null && !mQrImage.isRecycled()) {
            mQrImage.recycle();
        }
        mQrImage = null;
    }
}
