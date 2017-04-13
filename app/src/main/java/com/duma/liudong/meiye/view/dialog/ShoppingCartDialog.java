package com.duma.liudong.meiye.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.model.GouWuCheBean;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudong on 17/4/10.
 */

public class ShoppingCartDialog extends Dialog {

    private List<GouWuCheBean.CartListBean> mList;

    private RecyclerView rv_jiesuan;
    private LinearLayout layout_back;
    private CommonAdapter<GouWuCheBean.CartListBean> adapter;
    private Activity mActivity;

    public ShoppingCartDialog(@NonNull Activity context) {
        super(context, R.style.PopupDialog);
        mList = new ArrayList<>();
        this.mActivity = context;
    }

    public void setList(List<GouWuCheBean.CartListBean> list) {
        mList.clear();
        mList.addAll(list);
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).getMark().getTotal() == 0) {
                mList.remove(i);
                i--;
            }
        }
    }

    @Override
    public void show() {
        super.show();
        adapter.notifyDataSetChanged();
        if (mList.size() == 0) {
            dismiss();
            Ts.setText("请选择商品计算!");
            return;
        }
        if (mList.size() == 1) {
            dismiss();
            StartUtil.toQueRenDinDan(mActivity, mList.get(0).getMark().getStore_id());
            return;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window win = this.getWindow();
        win.setGravity(Gravity.BOTTOM);                       //从下方弹出
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //宽度填满
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;  //高度自适应
        win.setAttributes(lp);
        setContentView(R.layout.dialog_shoppingcart);
        this.setCancelable(false);//不点击不能取消
        init();
    }

    private void init() {
        rv_jiesuan = (RecyclerView) findViewById(R.id.rv_jiesuan);
        layout_back = (LinearLayout) findViewById(R.id.layout_back);
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        rv_jiesuan.setFocusable(false);
        rv_jiesuan.setNestedScrollingEnabled(false);
        rv_jiesuan.setLayoutManager(new LinearLayoutManager(mActivity));
        initadapter();
        rv_jiesuan.setAdapter(adapter);
    }

    private void initadapter() {
        adapter = new CommonAdapter<GouWuCheBean.CartListBean>(mActivity, R.layout.rv_shoppingcart_dialog, mList) {
            @Override
            protected void convert(ViewHolder holder, final GouWuCheBean.CartListBean cartListBean, int position) {
                holder.setText(R.id.tv_store_name, cartListBean.getMark().getStore_name());
                holder.setText(R.id.tv_total, "￥" + cartListBean.getMark().getTotal());
                holder.setText(R.id.tv_num, "共" + "2" + "件,合集:");
                holder.setOnClickListener(R.id.tv_jiesuan, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StartUtil.toQueRenDinDan(mActivity, cartListBean.getMark().getStore_id());
                    }
                });
//                LinearLayout linearLayout = holder.getView(R.id.layout_item);
//                if (cartListBean.getMark().getTotal() == 0) {
//                    linearLayout.setVisibility(View.GONE);
//                } else {
//                    linearLayout.setVisibility(View.VISIBLE);
//                }
            }
        };
    }
}
