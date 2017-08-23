package com.justdo.kangxidaojia.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.justdo.kangxidaojia.R;
import com.justdo.kangxidaojia.bean.HomeBean;
import com.justdo.kangxidaojia.util.Constants;
import com.justdo.kangxidaojia.util.QrUtil;
import com.justdo.kangxidaojia.util.UrlUtil;
import com.justdo.kangxidaojia.widget.MyViewPager;

import java.lang.reflect.Field;
import java.util.List;

import static com.justdo.kangxidaojia.R.id.tag_one_tv;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private final boolean                 mIsViewPager;
    private       LayoutInflater          mInflater;
    private       List<HomeBean.DataBean> mDatas;
    private       List<Integer>           heights;
    private       int                     currentPosition;
    private       MyViewPager             mMyViewPager;
    private       View                    mViewPagerView;
    private       View                    mFocusView;
    private       List<Bitmap>            mQRBitMaps;
    private Handler mHandler = new Handler();


    public void setQRBitMaps(List<Bitmap> QRBitMaps) {
        mQRBitMaps = QRBitMaps;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public interface OnItemSelectListener {
        void onItemSelect(View view, int position);
    }

    private OnItemClickListener  mListener;
    private OnItemSelectListener mSelectListener;

    public void setOnItemSelectListener(OnItemSelectListener listener) {
        mSelectListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public GalleryAdapter(Context context, List<HomeBean.DataBean> datas, boolean isViewPager) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.mIsViewPager = isViewPager;
        if (isViewPager && mMyViewPager == null) {
            Log.d("viewPager", "viewpager初始化");
            Constants.VIEW_PAGE = isViewPager;
            mMyViewPager = new MyViewPager(context);
            mHeaderCount = mMyViewPager.getViewPagerView().getVisibility();
            mViewPagerView = mMyViewPager.getViewPagerView();
            mFocusView = mMyViewPager.getFocusView();
            mViewPagerView.requestFocus();
        }
    }

    public void setFocus() {
        mFocusView.requestFocus();
    }

    // head
    public static final int ITEM_TYPE_HEADER = 1001;
    public static final int ITEM_TYPE_BODY   = 1002;
    private             int mHeaderCount     = 0;

    public boolean isHeaderView(int position) {
        if (mIsViewPager) {
            return position == 0;
        } else {
            return mIsViewPager;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (Constants.VIEW_PAGE && position == 0) {
            Log.d("visibility", "visibility" + position);
            return ITEM_TYPE_HEADER;
        }
        return ITEM_TYPE_BODY;
    }

    public void setDatas(List datas) {
        mDatas = datas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view;
        switch (viewType) {
            case ITEM_TYPE_HEADER:
                parent.setTag(ITEM_TYPE_HEADER);
                Log.d("viewPager", "viewpager添加");
                holder = new ViewHolder(mViewPagerView);
                break;
            case ITEM_TYPE_BODY:
                parent.setTag(ITEM_TYPE_BODY);
                holder = itemView(parent);
                break;
        }
        return holder;
    }

    @NonNull
    private ViewHolder itemView(ViewGroup parent) {
        View view;
        ViewHolder holder;
        view = mInflater.inflate(R.layout.home_item, parent, false);
        holder = new ViewHolder(view);
        holder.mTimer = (TextView) view.findViewById(R.id.item_timer);
        holder.mTital = (TextView) view.findViewById(R.id.item_tital);
        holder.mFullPrice = (TextView) view.findViewById(R.id.item_fullPrice);
        holder.mDiscPrice = (TextView) view.findViewById(R.id.item_discPrice);
        holder.mImg = (ImageView) view.findViewById(R.id.item_image);
        holder.tag_one = (ImageView) view.findViewById(R.id.tag_one);
        holder.tag_tow = (ImageView) view.findViewById(R.id.tag_tow);
        holder.tag_one_tv = (TextView) view.findViewById(tag_one_tv);
        holder.tag_tow_tv = (TextView) view.findViewById(R.id.tag_tow_tv);
        // holder.qr = (ImageView) view.findViewById(R.id.home_qr_rb);
        setHolderQR(holder, view);
        return holder;
    }

    //leftTop leftBottom rightTop rightBottom  "qrsize ": "200",      //二维码尺寸200*200 px
    private void setHolderQR(ViewHolder holder, View view) {
        if (!mIsViewPager && mDatas != null && mDatas.get(0) != null) {
            Qrpos(0, view, holder);
        } else if (mIsViewPager && mDatas != null && mDatas.get(1) != null) {
            Qrpos(1, view, holder);
        }
    }

    private void Qrpos(int i, View view, ViewHolder holder) {
        switch (mDatas.get(i).getQrpos()) {
            case "leftTop":
                holder.qr = (ImageView) view.findViewById(R.id.home_qr_lt);
                break;
            case "leftBottom":
                holder.qr = (ImageView) view.findViewById(R.id.home_qr_lb);
                break;
            case "rightBottom":
                holder.qr = (ImageView) view.findViewById(R.id.home_qr_rb);
                break;
            case "rightTop":
            default:
                holder.qr = (ImageView) view.findViewById(R.id.home_qr_rt);
                // holder.qr = (ImageView) view.findViewById(R.id.home_qr_rb);
                // holder.qr = (ImageView) view.findViewById(R.id.home_qr_lb);
                //holder.qr = (ImageView) view.findViewById(R.id.home_qr_lt);
                break;
        }
        if (holder.qr != null && mDatas.get(i).getQrsize() != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.qr.getLayoutParams();
            int id = getResId("px" + mDatas.get(i).getQrsize(), R.dimen.class);
            if (id != -1) {
                layoutParams.height = view.getContext().getResources().getDimensionPixelSize(id);
                layoutParams.width = view.getContext().getResources().getDimensionPixelSize(id);
            }
            holder.qr.setLayoutParams(layoutParams);
        }
    }

    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.itemView.setFocusable(true);
        holder.itemView.setTag(position);
        holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.i("adapter", "hasfocus:" + position + "--" + hasFocus);
                if (hasFocus) {
                    currentPosition = (int) holder.itemView.getTag();
                    mSelectListener.onItemSelect(holder.itemView, currentPosition);
                }
            }
        });
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mListener.onItemLongClick(v, holder.getLayoutPosition());
                    return true;
                }
            });
        }
        if (position == 0) {
            holder.itemView.requestFocus();
        }
        setView(holder, position);
    }

    private void setView(final ViewHolder holder, final int position) {
        if (!mIsViewPager) {
            holder.mDiscPrice.setText(mDatas.get(position).getProPrice());
            holder.mFullPrice.setText(mDatas.get(position).getFullPrice());
            holder.mTimer.setText("(" + mDatas.get(position).getTimeLength() + "分钟)");
            holder.mFullPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            holder.mFullPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            String string = nameSubString(mDatas.get(position).getProName());
            holder.mTital.setText(string);
            Glide.with(holder.mDiscPrice.getContext().getApplicationContext()).load(UrlUtil.BASE_PIC_URL + mDatas.get(position).getImgUrl()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.mImg);
            if (Integer.parseInt(mDatas.get(position).getForHome()) == 1 && Integer.parseInt(mDatas.get(position).getForStore()) == 1) {
                holder.tag_one_tv.setText("上门");
                holder.tag_tow_tv.setText("到店");
                holder.tag_one.setImageResource(R.mipmap.gohome);
                holder.tag_tow.setImageResource(R.mipmap.goshop);
            } else {
                if (Integer.parseInt(mDatas.get(position).getForHome()) == 1) {
                    holder.tag_one_tv.setText("上门");
                    holder.tag_one.setImageResource(R.mipmap.gohome);
                } else if (Integer.parseInt(mDatas.get(position).getForStore()) == 1) {
                    holder.tag_one_tv.setText("到店");
                    holder.tag_one.setImageResource(R.mipmap.goshop);
                }
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (holder.mQrImage == null || (int)holder.itemView.getTag() != position) {
                        holder.mQrImage = QrUtil.createQRImage(mDatas.get(position).getQrcodeUrl());
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            holder.qr.setImageBitmap(holder.mQrImage);
                        }
                    });
                }
            }).start();
        } else if (position != 0) {
            holder.mDiscPrice.setText(mDatas.get(position - 1).getProPrice());
            holder.mFullPrice.setText(mDatas.get(position - 1).getFullPrice());
            holder.mTimer.setText("(" + mDatas.get(position - 1).getTimeLength() + "分钟)");
            holder.mFullPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
            holder.mFullPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            String string = nameSubString(mDatas.get(position - 1).getProName());
            holder.mTital.setText(string);
            Glide.with(holder.mDiscPrice.getContext().getApplicationContext()).load(UrlUtil.BASE_PIC_URL + mDatas.get(position - 1).getImgUrl()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.mImg);
            if (Integer.parseInt(mDatas.get(position - 1).getForHome()) == 1 && Integer.parseInt(mDatas.get(position - 1).getForStore()) == 1) {
                holder.tag_one_tv.setText("上门");
                holder.tag_tow_tv.setText("到店");
                holder.tag_one.setImageResource(R.mipmap.gohome);
                holder.tag_tow.setImageResource(R.mipmap.goshop);
            } else {
                if (Integer.parseInt(mDatas.get(position - 1).getForHome()) == 1) {
                    holder.tag_one_tv.setText("上门");
                    holder.tag_one.setImageResource(R.mipmap.gohome);
                } else if (Integer.parseInt(mDatas.get(position - 1).getForStore()) == 1) {
                    holder.tag_one_tv.setText("到店");
                    holder.tag_one.setImageResource(R.mipmap.goshop);
                }
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (holder.mQrImage == null || (int)holder.itemView.getTag() != position - 1) {
                        holder.mQrImage = QrUtil.createQRImage(mDatas.get(position - 1).getQrcodeUrl());
                    }
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            holder.qr.setImageBitmap(holder.mQrImage);
                        }
                    });
                }
            }).start();
        }
    }

    private String nameSubString(String proName) {
        byte[] bytes = proName.getBytes();
        String substring = proName;
        if (proName.length() > 7) {
            substring = proName.substring(0, 6);
        }
        return substring;
    }

    @Override
    public int getItemCount() {
        if (mIsViewPager) {
            return mDatas == null ? 0 : mDatas.size() + 1;
        } else {
            return mDatas == null ? 0 : mDatas.size();
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        ImageView qr;
        View      view;
        TextView  mTimer;
        TextView  mFullPrice;
        TextView  mDiscPrice;
        TextView  mTital;
        ImageView tag_one;
        ImageView tag_tow;
        TextView  tag_one_tv;
        TextView  tag_tow_tv;
        Bitmap    mQrImage;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
