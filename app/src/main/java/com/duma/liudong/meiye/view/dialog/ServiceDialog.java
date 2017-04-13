package com.duma.liudong.meiye.view.dialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.duma.liudong.meiye.utils.Constants;

/**
 * Created by liudong on 17/3/22.
 */

public class ServiceDialog {
    private QueRenUtilDialog queRenUtilDialog;

    public ServiceDialog(final Activity activity) {
        queRenUtilDialog = new QueRenUtilDialog(activity, "联系客服", Constants.service, "取消", "呼叫");
        queRenUtilDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                activity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + Constants.service)));
            }
        });
    }

    public ServiceDialog(final Activity activity, final String tel) {
        queRenUtilDialog = new QueRenUtilDialog(activity, "联系客服", Constants.service, "取消", "呼叫");
        queRenUtilDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                activity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel)));
            }
        });
    }

    public void Show() {
        queRenUtilDialog.show();
    }

}
