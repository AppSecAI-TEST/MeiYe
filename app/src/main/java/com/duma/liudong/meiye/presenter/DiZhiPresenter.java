package com.duma.liudong.meiye.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.DiZhiBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.QueRenUtilDialog;
import com.duma.liudong.meiye.view.me.DiZhiFragment;
import com.duma.liudong.meiye.view.me.TianJiaDiZhiActivity;
import com.google.gson.Gson;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 79953 on 2016/10/28.
 */

public class DiZhiPresenter {

    private DiZhiListener diZhiListener;
    private Activity activity;
    private RecyclerView rvDizhiList;
    private List<DiZhiBean> mlist;
    private CommonAdapter<DiZhiBean> commonAdapter;
    private QueRenUtilDialog dialog;

    private String id;

    private DiZhiFragment fragment;

    public DiZhiPresenter(DiZhiListener diZhiListener, Activity activity, RecyclerView rvDizhiList, DiZhiFragment fragment) {
        this.diZhiListener = diZhiListener;
        this.activity = activity;
        this.rvDizhiList = rvDizhiList;
        this.fragment = fragment;
        mlist = new ArrayList<>();
        this.rvDizhiList.setLayoutManager(new LinearLayoutManager(activity));
        initData();
        rvDizhiList.setAdapter(commonAdapter);

        dialog = new QueRenUtilDialog(activity, "", "确定要删除该地址嘛？", "取消", "删除");
        dialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                delectDiZhi(id);
            }
        });
    }

    private void initData() {
        commonAdapter = new CommonAdapter<DiZhiBean>(activity, R.layout.rv_dizhi_list, mlist) {
            @Override
            protected void convert(ViewHolder holder, final DiZhiBean diZhiBean, int position) {
                holder.setText(R.id.tv_name, diZhiBean.getConsignee());
                holder.setText(R.id.tv_dianhua, diZhiBean.getMobile() + "");
                holder.setText(R.id.tv_xiangxidizhi, diZhiBean.getProvince() + diZhiBean.getCity() + diZhiBean.getDistrict() + diZhiBean.getAddress());
                final CheckBox cb_moren = holder.getView(R.id.cb_moren);
                if (diZhiBean.getIs_default().equals("1")) {
                    cb_moren.setChecked(true);
                } else {
                    cb_moren.setChecked(false);
                }
                holder.setOnClickListener(R.id.img_shanchu, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        id = diZhiBean.getAddress_id();
                        dialog.show();
                    }
                });

                cb_moren.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_UP:
                                if (diZhiBean.getIs_default().equals("1")) {
                                    Ts.setText("该地址已经设为默认地址~");
                                    cb_moren.setChecked(true);
                                    return true;
                                }
                                setMoRenDiZhi(diZhiBean.getAddress_id());
                                break;
                        }
                        return true;
                    }
                });

                holder.setOnClickListener(R.id.img_bianji, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, TianJiaDiZhiActivity.class);
                        intent.putExtra("type", "bianji");
                        intent.putExtra("bean", diZhiBean);
                        fragment.startActivityForResult(intent, 1);
                    }
                });


            }
        };
    }

    // LD: 获取地址列表
    public void getDiZhiHttp() {
        diZhiListener.loading_show();
        OkHttpUtils.getInstance().cancelTag("getDiZhiHttp");
        OkHttpUtils
                .get()
                .tag("getDiZhiHttp")
                .url(Api.addressList)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        mlist.clear();
                        try {
                            JSONArray jsonArray = new JSONArray(result);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                mlist.add(new Gson().fromJson(jsonArray.getString(i), DiZhiBean.class));
                            }
                        } catch (JSONException e) {
                            Ts.JsonErroy();
                        }
                        diZhiListener.is_kong(mlist);
                        commonAdapter.notifyDataSetChanged();
                        diZhiListener.loading_hide();
                    }

                    @Override
                    public void onError(String result) {
                        mlist.clear();
                        diZhiListener.is_kong(mlist);
                        diZhiListener.loading_hide();
                    }
                });
    }

    // LD: 设为默认  
    public void setMoRenDiZhi(String address_id) {
        diZhiListener.loading_show();
        OkHttpUtils.getInstance().cancelTag("setMoRenDiZhi");
        OkHttpUtils
                .get()
                .tag("setMoRenDiZhi")
                .url(Api.setDefaultAddr)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("address_id", address_id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        getDiZhiHttp();
                    }

                    @Override
                    public void onError(String result) {
                        diZhiListener.loading_hide();
                    }
                });
    }


    // LD: 删除地址
    public void delectDiZhi(String address_id) {
        diZhiListener.loading_show();
        OkHttpUtils.getInstance().cancelTag("delectDiZhi");
        OkHttpUtils
                .get()
                .tag("delectDiZhi")
                .url(Api.delAddress)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("address_id", address_id)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        getDiZhiHttp();
                    }

                    @Override
                    public void onError(String result) {
                        diZhiListener.loading_hide();
                    }
                });
    }
}
