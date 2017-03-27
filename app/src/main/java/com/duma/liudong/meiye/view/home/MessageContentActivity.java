package com.duma.liudong.meiye.view.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.base.MessageBean;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.MessageContentBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.ImageLoader;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.QueRenUtilDialog;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.RequestCall;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/27.
 */

public class MessageContentActivity extends BaseActivity implements BaseRvAdapter.RvAdapterListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;
    @BindView(R.id.rv_shangping)
    RecyclerView rvShangping;
    @BindView(R.id.sw_loading)
    SwipeRefreshLayout swLoading;
    private String type, title;

    private BaseRvAdapter<MessageBean> adapter;
    private RequestCall build;
    private RequestCall canleBulod;
    private QueRenUtilDialog dialog;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_message_content);
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title");
        ImageLoader.with(R.drawable.img_26, imgOther);
        tvTitle.setText(title);
        StartUtil.setSw(swLoading, this);
        rvShangping.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new BaseRvAdapter<MessageBean>(mActivity, R.layout.rv_message_content, rvShangping, this) {
        };
        build = OkHttpUtils.get()
                .url(Api.messageAction)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("type", type)
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token)).build();
        canleBulod = OkHttpUtils.get()
                .url(Api.messageAction)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("type", type)
                .addParams("is_del", "1")
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token)).build();
        adapter.QueryHttp(build);
        dialog = new QueRenUtilDialog(mActivity, "", "是否清空消息", "否", "是");
        dialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                canleBulod.execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        Ts.setText("清空成功!");
                        finish();
                    }
                });
            }
        });
    }

    @OnClick({R.id.layout_back, R.id.img_other})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.img_other:
                dialog.show();
                break;
        }
    }

    @Override
    public void hide_Kong() {

    }

    @Override
    public void show_kong() {

    }

    @Override
    public void hide_loading() {
        swLoading.setRefreshing(false);
    }

    @Override
    public void show_loading() {
        swLoading.setRefreshing(true);
    }

    @Override
    public void convert(ViewHolder holder, Object object, int position) {
        MessageContentBean bean = (MessageContentBean) object;
        holder.setText(R.id.tv_date, bean.getDate());
        holder.setText(R.id.tv_head, bean.getHead());
        holder.setText(R.id.tv_body, bean.getBody());
    }

    @Override
    public void onRefresh() {
        adapter.QueryHttp(build);
    }
}
