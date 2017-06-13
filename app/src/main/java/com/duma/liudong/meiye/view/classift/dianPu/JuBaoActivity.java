package com.duma.liudong.meiye.view.classift.dianPu;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.ImgUrlBean;
import com.duma.liudong.meiye.presenter.PhotoSelectUtil;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by liudong on 2017/6/12.
 */

public class JuBaoActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.edi_res)
    EditText ediRes;
    @BindView(R.id.rv_photo)
    RecyclerView rvPhoto;
    @BindView(R.id.btn_ok)
    Button btnOk;
    private String type, id;
    private PhotoSelectUtil photoSelectUtil;
    private List<String> mImgUrlList;
    int num = 0;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jubao);
    }

    @Override
    protected void initData() {
        tvTitle.setText("我要举报");
        type = getIntent().getStringExtra("type");
        id = getIntent().getStringExtra("id");
        mImgUrlList = new ArrayList<>();
        photoSelectUtil = new PhotoSelectUtil(mActivity, rvPhoto);

    }


    private void addTu() {
        DialogUtil.show(mActivity);
        num = 0;
        final int size = photoSelectUtil.getmList().size();
        mImgUrlList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            File file;
            if (photoSelectUtil.getmList().get(i).isCompressed() || (photoSelectUtil.getmList().get(i).isCut() && photoSelectUtil.getmList().get(i).isCompressed())) {
                file = new File(photoSelectUtil.getmList().get(i).getCompressPath());
            } else {
                file = new File(photoSelectUtil.getmList().get(i).getPath());
            }
            OkHttpUtils.getInstance().cancelTag(i);
            OkHttpUtils
                    .post()
                    .tag(i)
                    .url(Api.upload)
                    .addFile("pic", file.getName(), file)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            initRes(size);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            ImgUrlBean imgUrlBean = new Gson().fromJson(response, ImgUrlBean.class);
                            if (imgUrlBean.getStatus().equals("1")) {
                                mImgUrlList.add(imgUrlBean.getResult().getPath());
                                initRes(size);
                            } else {
                                Ts.setText(imgUrlBean.getMsg());
                            }

                        }
                    });
        }
    }

    private void initRes(int size) {
        num++;
        if (num == size) {
            FaTei();
        }
    }

    private void FaTei() {
        DialogUtil.show(mActivity);
        OkHttpUtils
                .post()
                .url(Api.jubao)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("content", ediRes.getText().toString())
                .addParams("store_id", type.equals("店铺") ? id : "")
                .addParams("goods_id", type.equals("店铺") ? "" : id)
                .addParams("type", type)
                .addParams("img_json", new Gson().toJson(mImgUrlList))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("举报成功!");
                        finish();
                    }
                });
    }

    @OnClick({R.id.layout_back, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.btn_ok:
                if (ediRes.getText().toString().isEmpty()) {
                    Ts.setText("请填写举报内容~");
                    return;
                }
                if (photoSelectUtil.getmList().size() == 0) {
                    FaTei();
                }
                addTu();
                break;
        }
    }
}
