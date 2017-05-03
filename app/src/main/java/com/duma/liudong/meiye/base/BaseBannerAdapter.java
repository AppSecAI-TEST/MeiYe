package com.duma.liudong.meiye.base;

import android.view.View;

import com.duma.liudong.meiye.widget.VerticalBannerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 * <p/>
 * Created by rowandjj(chuyi)<br/>
 * Date: 16/1/6<br/>
 * Time: 下午4:59<br/>
 */
@SuppressWarnings("unused")
public abstract class BaseBannerAdapter<T> {
    private List<T> mDatas;
    private OnDataChangedListener mOnDataChangedListener;

    public BaseBannerAdapter(List<T> datas) {
        mDatas = datas;
        if (datas == null || datas.isEmpty()) {
            throw new RuntimeException("nothing to show");
        }
    }

    public BaseBannerAdapter(T[] datas) {
        mDatas = new ArrayList<>(Arrays.asList(datas));
    }

    /**
     * 设置banner填充的数据
     */
    public void setData(List<T> datas) {
        this.mDatas = datas;
        notifyDataChanged();
    }

    public void setOnDataChangedListener(OnDataChangedListener listener) {
        mOnDataChangedListener = listener;
    }

    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void notifyDataChanged() {
        mOnDataChangedListener.onChanged();
    }

    public T getItem(int position) {
        return mDatas.get(position);
    }

    /**
     * 设置banner的样式
     */
    public abstract View getView(VerticalBannerView parent);

    /**
     * 设置banner的数据
     */
    public abstract void setItem(View view, T data);


    public interface OnDataChangedListener {
        void onChanged();
    }
}
