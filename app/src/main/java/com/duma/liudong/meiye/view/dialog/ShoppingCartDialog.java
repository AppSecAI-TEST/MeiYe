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
        mList.addAll(list);
//        adapter.notifyDataSetChanged();
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
        rv_jiesuan.setLayoutManager(new LinearLayoutManager(mActivity));
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
            }
        };
        rv_jiesuan.setAdapter(adapter);
    }
}
