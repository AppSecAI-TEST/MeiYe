package com.duma.liudong.meiye.view.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.ForumBean;
import com.duma.liudong.meiye.model.ImgUrlBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.LunTanFenLeiDialog;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.iwf.photopicker.widget.MultiPickResultView;
import okhttp3.Call;

/**
 * Created by liudong on 17/4/11.
 */

public class FaTieActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.layout_fatie)
    LinearLayout layoutFatie;
    @BindView(R.id.recycler_view)
    MultiPickResultView recyclerView;
    @BindView(R.id.tv_fenlei_name)
    TextView tvFenleiName;
    @BindView(R.id.layout_fenlei)
    LinearLayout layoutFenlei;
    @BindView(R.id.img_head_pic)
    ImageView imgHeadPic;
    @BindView(R.id.edit_text)
    EditText editText;

    public List<ForumBean> list;

    private LunTanFenLeiDialog dialog;
    private ForumBean bean;
    int num = 0;
    private List<String> mImgUrlList;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fatie);
    }

    @Override
    protected void initData() {
        list = (List<ForumBean>) getIntent().getSerializableExtra("list");
        mImgUrlList = new ArrayList<>();
        recyclerView.init(this, MultiPickResultView.ACTION_SELECT, null);
        dialog = new LunTanFenLeiDialog(mActivity, list);
        dialog.setOnFenLeiOnlick(new LunTanFenLeiDialog.OnFenLeiOnlick() {
            @Override
            public void fenlei(ForumBean forumBean) {
                bean = forumBean;
                tvFenleiName.setText(bean.getCat_name());
            }
        });
        ImageLoader.withYuan(Api.url + MyApplication.getSpUtils().getString(Constants.head_pic), imgHeadPic);
        tvName.setText(MyApplication.getSpUtils().getString(Constants.nickname));
    }

    @OnClick({R.id.layout_back, R.id.layout_other, R.id.layout_fenlei})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_other:
                if (bean == null) {
                    Ts.setText("请选择话题分类");
                    return;
                }
                if (editText.getText().toString().equals("")) {
                    Ts.setText("说点什么吧...");
                    return;
                }
                if (recyclerView.getPhotos().size() == 0) {
                    FaTei();
                }
                addTu();
                break;
            case R.id.layout_fenlei:
                dialog.show();
                break;
        }
    }

    private void addTu() {
        DialogUtil.show(mActivity);
        num = 0;
        final int size = recyclerView.getPhotos().size();
        mImgUrlList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            File file = new File(recyclerView.getPhotos().get(i));
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
                            mImgUrlList.add(imgUrlBean.getResult().getPath());
                            initRes(size);
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
        OkHttpUtils
                .post()
                .url(Api.add)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("cat_id", bean.getCat_id())
                .addParams("content", editText.getText().toString())
                .addParams("img_json", new Gson().toJson(mImgUrlList))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("发帖成功!");
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //onActivityResult里一行代码回调
        recyclerView.onActivityResult(requestCode, resultCode, data);
    }
}
