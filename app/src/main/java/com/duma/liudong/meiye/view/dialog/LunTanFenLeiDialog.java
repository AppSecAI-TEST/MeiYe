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
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.model.ForumBean;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by liudong on 17/4/10.
 */

public class LunTanFenLeiDialog extends Dialog {

    private List<ForumBean> mList;

    private RecyclerView rv_jiesuan;
    private LinearLayout layout_back;
    private CommonAdapter<ForumBean> adapter;
    private Activity mActivity;

    public LunTanFenLeiDialog(@NonNull Activity context, List<ForumBean> list) {
        super(context, R.style.PopupDialog);
        this.mList = list;
        this.mActivity = context;
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
        setContentView(R.layout.dialog_fenlei);
        this.setCancelable(false);//不点击不能取消
        init();
    }

    private void init() {
        layout_back = (LinearLayout) findViewById(R.id.layout_back);
        rv_jiesuan = (RecyclerView) findViewById(R.id.rv_fenlei);
        layout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        rv_jiesuan.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new CommonAdapter<ForumBean>(mActivity, R.layout.rv_dialog_fenlei, mList) {
            @Override
            protected void convert(ViewHolder holder, final ForumBean forumBean, int position) {
                TextView tv_fenlei = holder.getView(R.id.tv_fenlei);
                tv_fenlei.setText(forumBean.getCat_name());
                tv_fenlei.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        onFenLeiOnlick.fenlei(forumBean);
                    }
                });
            }
        };
        rv_jiesuan.setAdapter(adapter);
    }

    private OnFenLeiOnlick onFenLeiOnlick;

    public interface OnFenLeiOnlick {
        void fenlei(ForumBean bean);
    }

    public void setOnFenLeiOnlick(OnFenLeiOnlick onFenLeiOnlick) {
        this.onFenLeiOnlick = onFenLeiOnlick;
    }
}
