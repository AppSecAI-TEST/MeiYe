package com.duma.liudong.meiye.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.model.TieziBean;


public class LunTanDialog extends Dialog {

    private Context context;
    private onClickListenerInterface clickListenerInterface;
    private TieziBean tieziBean;

    private TextView tv_shoucang;
    private TextView tv_guanzhu;

    public interface onClickListenerInterface {
        void onGuanZhu(TieziBean zhiDingBean, TextView tv_guanzhu);

        void onShouCang(TieziBean zhiDingBean, TextView tv_shoucang);
    }

    public void setClicklistener(onClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    public LunTanDialog(Context context, TieziBean zhiDingBean) {
        super(context);
        this.context = context;
        this.tieziBean = zhiDingBean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_luntan);
        init();
    }


    private void init() {
        tv_shoucang = (TextView) findViewById(R.id.tv_shoucang);
        tv_guanzhu = (TextView) findViewById(R.id.tv_guanzhu);

        if (tieziBean.getIs_follow().equals("0")) {
            //没收藏
            tv_shoucang.setText("收藏");
        } else {
            tv_shoucang.setText("取消收藏");
        }
        if (tieziBean.getIs_user_follow().equals("0")) {
            //没收藏
            tv_guanzhu.setText("关注");
        } else {
            tv_guanzhu.setText("取消关注");
        }

        tv_shoucang.setOnClickListener(new clickListener());
        tv_guanzhu.setOnClickListener(new clickListener());
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.tv_shoucang:
                    dismiss();
                    clickListenerInterface.onShouCang(tieziBean, tv_shoucang);
                    break;
                case R.id.tv_guanzhu:
                    dismiss();
                    clickListenerInterface.onGuanZhu(tieziBean, tv_guanzhu);
                    break;

            }
        }
    }

    ;
}
