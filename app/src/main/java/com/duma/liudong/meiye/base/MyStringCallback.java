package com.duma.liudong.meiye.base;

import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.Lg;
import com.duma.liudong.meiye.utils.Ts;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;


public abstract class MyStringCallback extends StringCallback {
    public abstract void onMySuccess(String result);

    protected void onError(String result) {
    }


    @Override
    public void onError(Call call, Exception e, int id) {
        Lg.e("请求错误 " + id + ":" + e.getMessage());
        DialogUtil.hide();
        onError(e.getMessage());
        if (e.getMessage() != null) {
            switch (e.getMessage()) {
                case "Canceled":
                    Lg.e("上一个网络请求取消");
                    return;
                case "Socket closed":
                    Lg.e("上一个网络请求取消");
                    return;
            }
        }
        Ts.setText("网络连接错误！请检查您的网络连接");
    }

    @Override
    public void onResponse(String response, int id) {
        Lg.e("json: " + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            String status = jsonObject.getString("status");
            if (status.equals("1")) {
                onMySuccess(jsonObject.getString("result"));
            } else if (status.equals("100")) {
                onMySuccess("-1");
            } else {
                Ts.setText(jsonObject.getString("msg"));
                DialogUtil.hide();
                onError(status);
            }
        } catch (JSONException e) {
            Ts.erroy();
        }

    }
}