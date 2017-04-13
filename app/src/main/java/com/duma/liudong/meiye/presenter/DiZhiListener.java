package com.duma.liudong.meiye.presenter;


import com.duma.liudong.meiye.model.DiZhiBean;

import java.util.List;

/**
 * Created by 79953 on 2016/10/28.
 */

public interface DiZhiListener {
    void loading_show();

    void loading_hide();

    void is_kong(List<DiZhiBean> mlist);
}
