package com.duma.liudong.meiye.presenter;

import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.utils.Api;
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
                        getCodeListener.GetCodeSuccess();
                    }
                });
    }
}
