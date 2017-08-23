package com.justdo.kangxidaojia.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.justdo.kangxidaojia.R;
import com.justdo.kangxidaojia.adapter.GalleryAdapter;
import com.justdo.kangxidaojia.bean.ADBean;
import com.justdo.kangxidaojia.bean.HomeBean;
import com.justdo.kangxidaojia.util.Constants;
import com.justdo.kangxidaojia.util.OkHttpUtil;
import com.justdo.kangxidaojia.util.UrlUtil;
import com.justdo.kangxidaojia.widget.CustomRecycleView;
import com.justdo.kangxidaojia.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    public static final int FIRST_POSITION       = 0;
    public static final int COLUMN_GRID          = 2;
    public static final int CLOSE_VIEWPAGER_SIZE = 1;
    private CustomRecycleView       mRecyclerView;
    private GalleryAdapter          mAdapter;
    private List<HomeBean.DataBean> mDatas;
    private boolean                 mViewPager;
    private        boolean isViewPager = true;
    private        int     keyCount    = 0;
    private static int[]   KEY         = {19, 19, 20, 20, 21, 21, 22, 22, 82};
    private TextView mHomeTital;
    private int          isTow       = 1;
    private Handler      mHandler    = new Handler();
    private int          oldposition = -1;
    private List<Bitmap> mQRBitmaps  = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Constants.DESTROY = true;
        isViewPager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Constants.DESTROY = false;
    }

    private void initGlidlayout() {
        mHomeTital = (TextView) findViewById(R.id.home_tital_name);
        mRecyclerView = (CustomRecycleView) findViewById(R.id.id_recyclerview_horizontal);
        mAdapter = new GalleryAdapter(this, mDatas, isViewPager);
        mAdapter.setQRBitMaps(mQRBitmaps);
      //  QrNotifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        final GridLayoutManager layout = new GridLayoutManager(this, COLUMN_GRID, RecyclerView.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layout);
        mRecyclerListener(linearLayoutManager, layout);
    }

  /*  private void QrNotifyDataSetChanged() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAdapter != null) {
                    View focusedChild = mRecyclerView.getFocusedChild();
                    mAdapter.notifyDataSetChanged();
                    if (focusedChild != null)
                    focusedChild.requestFocus();
                }
            }
        }, 2000);
    }*/

    private void mRecyclerListener(final LinearLayoutManager linearLayoutManager, final GridLayoutManager layout) {
        layout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return mAdapter.isHeaderView(position) ? layout.getSpanCount() : CLOSE_VIEWPAGER_SIZE;
            }
        });
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (isViewPager && position != FIRST_POSITION) {
                    Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                    intent.putExtra("ItemID", mDatas.get(position - 1).getId());
                    Log.e("itemId", "itemId " + mDatas.get(position - 1).getId());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
                    intent.putExtra("ItemID", mDatas.get(position).getId());
                    Log.e("itemId", "itemId " + mDatas.get(position).getId());
                    startActivity(intent);
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        });

        mAdapter.setOnItemSelectListener(new GalleryAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelect(View view, int position) {
                Log.d("position11", "position: " + position);
                //显示viewpager时焦点
                showFocus(position);
            }
        });
        mRecyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("hasFocus", "hasFocus" + hasFocus);
                if (hasFocus) {
                    if (mRecyclerView.getChildCount() > 0) {
                        linearLayoutManager.scrollToPositionWithOffset(0, 0);
                        //mRecyclerView.getChildAt(0).requestFocus();
                    }
                }
            }
        });
    }

    private void showFocus(int position) {
        if (isViewPager) {
            if (position == 0 && isViewPager && isTow == 1 /* && mRecyclerView.getChildAt(0) != null*/) {
                Log.d("position11", "mRecyclerView.getChildAt(0).requestFocus(): ");
                mAdapter.setFocus();
            } else if (mRecyclerView.getChildCount() > 3 && position == 0 && isTow == 2 && isViewPager) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (oldposition != -1) {
                            mRecyclerView.getChildAt(oldposition).requestFocus();
                        }
                    }
                }, 50);
            }
            if (isViewPager && position != 1 && position != 2) {
                isTow = 1;
            }
            mRecyclerView.smoothToCenter(position);
            if (position == 1 || position == 2) {
                oldposition = position;
            }
        }
    }

    private void initDatas() {
        final String format;
        if (Integer.parseInt(Constants.SID) != -1) {
            format = String.format(UrlUtil.PROJECT_URL, Constants.SID, Constants.MID);
        } else {
            format = String.format(UrlUtil.PROJECT_URL_TOW, Constants.MID);
        }
        Log.d("Homeformat", "Homeformat" + format);
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.netGetData(format, HomeBean.class).setOnPushData(new OkHttpUtil.PushData<HomeBean>() {
            @Override
            public void onPushData(HomeBean homeBean) {
                mDatas = homeBean.getData();
                if (homeBean.getMsg() != null) {
                    mHomeTital.setText("-" + homeBean.getMsg());
                } else if (homeBean.getMsg().isEmpty()) {
                    mHomeTital.setText("");
                }
                //creatQR();
                initGlidlayout();
            }
        });
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d("KeyEventHomeActivity", "KeyEventHomeActivity :" + event.getKeyCode());
            if (KEY[keyCount] == event.getKeyCode()) {
                keyCount++;
            } else {
                if (KEY[0] == event.getKeyCode()) {
                    keyCount = 1;
                } else {
                    keyCount = 0;
                }
            }
            if (keyCount == 9) {
                keyCount = 0;
                Constants.KEY_SHOP_ID = true;
                Log.e("KeyEventHomeActivity", "KeyEventHomeActivity :" + event.getKeyCode());
                startActivity(new Intent(this, SplashActivity.class));
                finish();
            }
            //向左
            if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
                isTow = 2;
            } else {
                isTow = 1;
            }
        }

        return super.dispatchKeyEvent(event);
    }

    public boolean isViewPager() {
        String format;
        if (Integer.parseInt(Constants.SID) != -1) {
            format = String.format(UrlUtil.AD_URL, Constants.SID, 1, Constants.MID);
        } else {
            format = String.format(UrlUtil.AD_URL_TOW, 1, Constants.MID);
        }
        Log.d("formatviewPager", "formatviewPager" + format);
        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.netGetData(format, ADBean.class).setOnPushData(new OkHttpUtil.PushData<ADBean>() {
            @Override
            public void onPushData(ADBean adBean) {
                isViewPager = true;
                if (adBean.getData() == null || adBean.getData().size() == 0) {

                    isViewPager = false;
                    Constants.VIEW_PAGE = false;
                }
                Log.e("isViewPager", "isViewPager :" + isViewPager);
                initDatas();
                initGlidlayout();
            }
        });
        return mViewPager;
    }
   /* private void creatQR() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mDatas.size(); i++) {
                    String qrcodeUrl = mDatas.get(i).getQrcodeUrl();
                    mQRBitmaps.add(i, QrUtil.createQRImage(qrcodeUrl));
                }
              //  QrNotifyDataSetChanged();
            }
        }).start();

    }*/
}
