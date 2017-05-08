package com.duma.liudong.meiye.view.dialog;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.PhoneBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by liudong on 17/3/22.
 */

public class ServiceDialog {
    private QueRenUtilDialog queRenUtilDialog;
    private Activity activity;

    public ServiceDialog(final Activity activity) {
        this.activity = activity;
    }

    public ServiceDialog(final Activity activity, final String tel) {
        queRenUtilDialog = new QueRenUtilDialog(activity, "联系客服", tel, "取消", "呼叫");
        queRenUtilDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                activity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel)));
            }
        });
    }

    public void Show() {
        if (activity == null) {
            queRenUtilDialog.show();
            return;
        }
        DialogUtil.show(activity);
        OkHttpUtils
                .get()
                .url(Api.phone)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        final PhoneBean phoneBean = new Gson().fromJson(result, PhoneBean.class);
                        queRenUtilDialog = new QueRenUtilDialog(activity, "联系客服", phoneBean.getPhone(), "取消", "呼叫");
                        queRenUtilDialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
                            @Override
                            public void onYes() {
                                activity.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneBean.getPhone())));
                            }
                        });
                        queRenUtilDialog.show();
                    }
                });

    }

}
