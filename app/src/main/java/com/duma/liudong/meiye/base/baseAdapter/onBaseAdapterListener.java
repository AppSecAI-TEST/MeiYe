package com.duma.liudong.meiye.base.baseAdapter;

import com.chad.library.adapter.base.BaseViewHolder;

public interface onBaseAdapterListener<T> {
    void getView(BaseViewHolder helper, T item);
}