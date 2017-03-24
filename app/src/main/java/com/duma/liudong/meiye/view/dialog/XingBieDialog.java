package com.duma.liudong.meiye.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.duma.liudong.meiye.R;


public class XingBieDialog extends Dialog {

    private Context context;
    private ClickListenerInterface clickListenerInterface;

    public interface ClickListenerInterface {
        void onXinBie(String type);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    public XingBieDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_xinbie);
        init();
    }

    private void init() {
        TextView tvLan = (TextView) findViewById(R.id.xinbie_lan);
        TextView tvLv = (TextView) findViewById(R.id.xinbie_lv);
        TextView tvbaomi = (TextView) findViewById(R.id.xinbie_baomi);

        tvLan.setOnClickListener(new clickListener());
        tvLv.setOnClickListener(new clickListener());
        tvbaomi.setOnClickListener(new clickListener());
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // LD: （0 保密 1 男 2 女）
            int id = v.getId();
            switch (id) {
                case R.id.xinbie_lan:
                    dismiss();
                    clickListenerInterface.onXinBie("1");
                    break;
                case R.id.xinbie_lv:
                    dismiss();
                    clickListenerInterface.onXinBie("2");
                    break;
                case R.id.xinbie_baomi:
                    dismiss();
                    clickListenerInterface.onXinBie("0");
                    break;

            }
        }
    }

    ;
}
