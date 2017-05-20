package com.duma.liudong.meiye.view.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.BasePopWindos;
import com.duma.liudong.meiye.database.dao.LiShiSouSuoBean;
import com.duma.liudong.meiye.database.dao.LiShiSouSuoDao;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.QueRenUtilDialog;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/4/19.
 */

public class SouSuoActivity extends BaseActivity implements QueRenUtilDialog.OnYesClickListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.edit_res)
    EditText editRes;
    @BindView(R.id.tv_sousuo)
    TextView tvSousuo;
    @BindView(R.id.btn_qingkong)
    Button btnQingkong;
    @BindView(R.id.layout_show)
    LinearLayout layoutShow;
    @BindView(R.id.rv_lishi)
    RecyclerView rvLishi;

    private BasePopWindos basePopWindos;
    private LinearLayout layout_baobei, layout_dianpu;
    private LiShiSouSuoDao liShiSouSuoDao;
    private List<LiShiSouSuoBean> mLiShilist;
    private QueRenUtilDialog queRenUtilDialog;
    private CommonAdapter<LiShiSouSuoBean> adapter;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sousuo);
    }

    @Override
    protected void initData() {
        mLiShilist = new ArrayList<>();
        liShiSouSuoDao = new LiShiSouSuoDao();
        basePopWindos = new BasePopWindos(mActivity, R.layout.pop_sousuo);
        queRenUtilDialog = new QueRenUtilDialog(mActivity, "", "是否清空历史搜索", "否", "是");
        queRenUtilDialog.setYesClicklistener(this);
        layout_baobei = (LinearLayout) basePopWindos.getView().findViewById(R.id.layout_baobei);
        layout_dianpu = (LinearLayout) basePopWindos.getView().findViewById(R.id.layout_dianpu);
        final TextView tv_name = (TextView) basePopWindos.getView().findViewById(R.id.tv_name);
        tv_name.setText(getIntent().getStringExtra("type"));
        tvType.setText(tv_name.getText());
        layout_baobei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basePopWindos.disMiss();
                tvType.setText(tv_name.getText());
            }
        });
        layout_dianpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basePopWindos.disMiss();
                tvType.setText("店铺");
            }
        });
        // LD: 初始化历史列表
        initLishiAdapter();
        rvLishi.setLayoutManager(new LinearLayoutManager(mActivity));
        rvLishi.setAdapter(adapter);
        shuaXinLiShi();
    }

    private void initLishiAdapter() {
        adapter = new CommonAdapter<LiShiSouSuoBean>(mActivity, R.layout.rv_lishi, mLiShilist) {
            @Override
            protected void convert(ViewHolder holder, LiShiSouSuoBean bean, int position) {
                holder.setText(R.id.tv_name, bean.getName());
            }
        };
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                editRes.setText(mLiShilist.get(position).getName());
                editRes.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                editRes.setSelection(editRes.getText().length());
                sousuo();

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        shuaXinLiShi();
    }

    @OnClick({R.id.layout_back, R.id.tv_type, R.id.tv_sousuo, R.id.btn_qingkong})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.tv_type:
                basePopWindos.Show(layoutShow);
                break;
            case R.id.tv_sousuo:
                if (editRes.getText().toString().equals("")) {
                    Ts.setText("请输入搜索内容!");
                    return;
                }
                sousuo();
                break;
            case R.id.btn_qingkong:
                queRenUtilDialog.show();
                break;
        }
    }

    private void sousuo() {
        if (tvType.getText().toString().equals("宝贝")) {
            StartUtil.toShangPingLieBiao(mActivity, Constants.keyword, editRes.getText().toString(), editRes.getText().toString(), "");
        } else if (tvType.getText().toString().equals("服务")) {
            StartUtil.toShangPingLieBiao(mActivity, Constants.keyword, editRes.getText().toString(), editRes.getText().toString(), "3");
        } else {
            //搜索店铺
            StartUtil.toDianPuList(mActivity, editRes.getText().toString());
        }
        LiShiSouSuoBean bean = new LiShiSouSuoBean();
        bean.setName(editRes.getText().toString());
        liShiSouSuoDao.add(bean);
    }

    private void shuaXinLiShi() {
        mLiShilist.clear();
        mLiShilist.addAll(liShiSouSuoDao.queryAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onYes() {
        liShiSouSuoDao.delete();
        shuaXinLiShi();
    }

}
