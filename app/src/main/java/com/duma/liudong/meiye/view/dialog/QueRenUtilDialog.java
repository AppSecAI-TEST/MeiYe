package com.duma.liudong.meiye.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.duma.liudong.meiye.R;


public class QueRenUtilDialog extends Dialog {

    private Context context;
    private OnYesClickListener yesClickListener;
    private OnNoClickListener noClickListener;
    private String biaoti, leirong, no, ok;

    public interface OnYesClickListener {
        void onYes();

    }

    public interface OnNoClickListener {
        void onNo();
    }

    public void setYesClicklistener(OnYesClickListener yesClickListener) {
        this.yesClickListener = yesClickListener;
    }

    public void setNoClicklistener(OnNoClickListener noClickListener) {
        this.noClickListener = noClickListener;
    }

    public QueRenUtilDialog(Context context, String biaoti, String leirong, String no, String ok) {
        super(context);
        this.context = context;
        this.biaoti = biaoti;
        this.leirong = leirong;
        this.no = no;
        this.ok = ok;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_querenutil);
        this.setCancelable(false);//不点击不能取消
        init();
    }

    private void init() {
        TextView qr_biaoti = (TextView) findViewById(R.id.qr_biaoti);
        TextView qr_leirong = (TextView) findViewById(R.id.qr_leirong);
        TextView qr_no = (TextView) findViewById(R.id.qr_no);
        TextView qr_ok = (TextView) findViewById(R.id.qr_ok);

        qr_biaoti.setText(biaoti);
        qr_leirong.setText(leirong);
        qr_no.setText(no);
        qr_ok.setText(ok);

        if (biaoti.equals("")) {
            qr_biaoti.setVisibility(View.GONE);
        }
        if (no.equals("")) {
            qr_no.setVisibility(View.GONE);
        }

        qr_no.setOnClickListener(new clickListener());
        qr_ok.setOnClickListener(new clickListener());
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            int id = v.getId();
            switch (id) {
                case R.id.qr_no:
                    dismiss();
                    if (noClickListener != null) {
                        noClickListener.onNo();
                    }
                    break;
                case R.id.qr_ok:
                    dismiss();
                    if (yesClickListener != null) {
                        yesClickListener.onYes();
                    }
                    break;

            }
        }
    }

    ;
}
