package com.duma.liudong.meiye.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;

import com.duma.liudong.meiye.R;

public class LoadineDialog extends Dialog {

    private boolean isBack = true;

    public LoadineDialog(Activity activity) {
        super(activity);
    }

    public LoadineDialog(Activity activity, boolean isBack) {
        super(activity);
        this.isBack = isBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_loadine);
        this.setCancelable(isBack);

//        ImageLoader.withGif((ImageView) findViewById(R.id.img_gif));
    }

}
