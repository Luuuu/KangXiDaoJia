package com.justdo.kangxidaojia.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.justdo.kangxidaojia.R;
import com.justdo.kangxidaojia.activity.WebLoadActivity;
import com.justdo.kangxidaojia.bean.ADBean;
import com.justdo.kangxidaojia.util.Constants;
import com.justdo.kangxidaojia.util.GsonUtil;
import com.justdo.kangxidaojia.util.UrlUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/17.
 */

public class MyViewPager implements View.OnClickListener {
    private Context               mContext;
    private List<ADBean.DataBean> imageResIds;
    private ViewPager             mViewPager;
    private TextView              tv_decription;
    private LinearLayout          mll_point_group;
    private int     prevousPosition = 0;
    private boolean isLoadingData   = true;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                initViewPager();
            } else if (msg.what == 2) {
                view.setVisibility(View.GONE);
            } else {
                if (Constants.DESTROY) {
                    int currentItem = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(currentItem + 1);
                    mHandler.sendEmptyMessageDelayed(0, 5000);
                }
            }
        }
    };
    private View           view;
    private ADBean         mADBean;
    private RelativeLayout mRelativeLayout;

    public MyViewPager(Context context) {
        this.mContext = context;
        view = LayoutInflater.from(mContext).inflate(R.layout.view_pager, null);
        mRelativeLayout = (RelativeLayout) view.findViewById(R.id.viewpager_relative);
        mRelativeLayout.setOnClickListener(this);
        mRelativeLayout.setFocusable(true);
    }

    private void getNetData() {
        String format = String.format(UrlUtil.AD_URL, Constants.SID, 1, Constants.MID);
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(format)
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
                    mADBean = GsonUtil.toBean(bean, ADBean.class);
                    imageResIds = mADBean.getData();
                    if (imageResIds == null) {
                        mHandler.sendEmptyMessage(2);
                        return;
                    }
                    Log.d("imageResIds", "imageResIds" + imageResIds.size() + "  " + imageResIds.toString());
                    mHandler.sendEmptyMessage(1);
                }
            }
        });
    }

    public MyViewPager getInstance() {
        return this;
    }

    public void startViewPager() {
        initViewPager();
    }

    private void initViewPager() {
        initView();
        initData();
        mViewPager.setCurrentItem(imageResIds.size() * 1000);
        mHandler.sendEmptyMessageDelayed(0, 2000);
    }

    private void initData() {
        for (int i = 0; i < imageResIds.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(6, 6);

            ImageView point = new ImageView(mContext);
            if (i != 0) {
                layoutParams.leftMargin = 5;
                point.setEnabled(false);
            }
            point.setImageResource(R.drawable.select_viewpager_dot);
            point.setLayoutParams(layoutParams);
            mll_point_group.addView(point);
        }
    }

    public View getViewPagerView() {
        if (isLoadingData) {
            isLoadingData = false;
            getNetData();
        }

        return view;
    }

    public View getFocusView() {
        return mRelativeLayout;
    }

    private void initView() {

        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mViewPager.setFocusable(false);
        mViewPager.setAdapter(new MyAdapter());
        mll_point_group = (LinearLayout) view.findViewById(R.id.ll_point_group);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int pos = position % imageResIds.size();
                System.out.println("onPageSelected:" + position);
                mll_point_group.getChildAt(prevousPosition).setEnabled(false);
                prevousPosition = pos;
                mll_point_group.getChildAt(pos).setEnabled(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void stopViewPager() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onClick(View view) {
        String url = mADBean.getData().get(mViewPager.getCurrentItem() % imageResIds.size()).getH5Url();
        if (!url.isEmpty() && url.startsWith("http://")) {
            Intent intent = new Intent(mContext, WebLoadActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("VEB_VIEW", url);
            mContext.startActivity(intent);
        }
    }

    private class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int pos = position % imageResIds.size();
            ImageView iv = new ImageView(mContext);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            String thumbnailUrl = mADBean.getData().get(pos).getThumbnailUrl();
            Glide.with(mContext.getApplicationContext()).load(UrlUtil.BASE_PIC_URL + thumbnailUrl).into(iv);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
