package com.duma.liudong.meiye.presenter;

import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.MeBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.Ts;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by liudong on 17/3/21.
 */

public class PublicPresenter {

    /**
     * 获取验证码
     */
    public interface GetCodeListener {
        void GetCodeSuccess();
    }

    private GetCodeListener getCodeListener;

    public void setGetCodeListener(GetCodeListener getCodeListener) {
        this.getCodeListener = getCodeListener;
    }

    public void getCode(String phone) {
        OkHttpUtils.getInstance().cancelTag("getCode");
        OkHttpUtils
                .post()
                .addParams("phone", phone)
                .url(Api.sendCode)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        Ts.setText("发送成功!");
                        getCodeListener.GetCodeSuccess();
                    }
                });
    }

    /**
     * 获取我的页面的信息
     */
    public interface GetMeListener {
        void GetMeSuccess(MeBean meBean);

        void GetMeError();
    }

    private GetMeListener getMeListener;

    public void setGetMeListener(GetMeListener getMeListener) {
        this.getMeListener = getMeListener;
    }

    public void getMe() {
        OkHttpUtils.getInstance().cancelTag("getMe");
        OkHttpUtils
                .post()
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .url(Api.index)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        getMeListener.GetMeSuccess(new Gson().fromJson(result, MeBean.class));
                    }

                    @Override
                    protected void onError(String result) {
                        getMeListener.GetMeError();
                        super.onError(result);
                    }
                });
    }

}
