package com.duma.liudong.meiye.view.me;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.model.DiZhiBean;
import com.duma.liudong.meiye.presenter.DiZhiListener;
import com.duma.liudong.meiye.presenter.DiZhiPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 79953 on 2016/10/12.
 */

public class DiZhiFragment extends BaseFragment implements DiZhiListener, SwipeRefreshLayout.OnRefreshListener {
    private static DiZhiFragment instance = null;
    @BindView(R.id.rv_dizhiList)
    RecyclerView rvDizhiList;
    @BindView(R.id.layout_addDizhi)
    LinearLayout layoutAddDizhi;
    @BindView(R.id.swr_loading)
    SwipeRefreshLayout swrLoading;
    @BindView(R.id.layout_dizhi_kong)
    LinearLayout layoutDizhiKong;

    DiZhiBean diZhiBean;

    public static DiZhiFragment newInstance() {
        if (instance == null) {
            instance = new DiZhiFragment();
        }
        return instance;
    }

    private DiZhiPresenter diZhiPresenter;


    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_dizhi;
    }

    @Override
    protected void initData() {
        diZhiBean = new DiZhiBean();
        swrLoading.setOnRefreshListener(this);
        swrLoading.setColorSchemeColors(getResources().getColor(R.color.maincolor));

        diZhiPresenter = new DiZhiPresenter(this, mActivity, rvDizhiList, this);
        swrLoading.setRefreshing(false);
        diZhiPresenter.getDiZhiHttp();

    }

    @Override
    public void loading_show() {
        swrLoading.setRefreshing(true);
    }

    @Override
    public void loading_hide() {
        swrLoading.setRefreshing(false);
    }

    @Override
    public void is_kong(List<DiZhiBean> mlist) {
        if (mlist.size() == 0) {
            layoutDizhiKong.setVisibility(View.VISIBLE);
        } else {
            layoutDizhiKong.setVisibility(View.GONE);
        }
        // LD: 返回给确认订单信息的默认地址
        boolean isEmpty = true;
        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).getIs_default().equals("1")) {
                isEmpty = false;
                diZhiBean = mlist.get(i);
                return;
            }
        }
        if (isEmpty) {
            diZhiBean = new DiZhiBean();
        }
    }

//    public Intent getType() {
//        Intent i = new Intent();
//        if (b.isEmpty()) {
//            i.putExtra("type", "-1");
//        } else {
//            i.putExtra("type", "0");
//            i.putExtras(b);
//        }
//        return i;
//    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().post(diZhiBean);
    }

    @OnClick(R.id.layout_addDizhi)
    public void onClick() {
        Intent intent = new Intent(mActivity, TianJiaDiZhiActivity.class);
        intent.putExtra("type", "add");
        startActivityForResult(intent, 1);
    }

    @Override
    public void onRefresh() {
        diZhiPresenter.getDiZhiHttp();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                diZhiPresenter.getDiZhiHttp();
            }
        }

    }
}
