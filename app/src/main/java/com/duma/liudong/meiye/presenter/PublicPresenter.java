package com.duma.liudong.meiye.presenter;

import android.app.Activity;

import com.duma.liudong.meiye.base.BaseRvAdapter;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.model.LinJuanBean;
import com.duma.liudong.meiye.model.MeBean;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
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


    //领取优惠券
    public void getYouHuiJuan(String id, final BaseRvAdapter<LinJuanBean> adapter, final int p) {
        OkHttpUtils.getInstance().cancelTag("getYouHuiJuan");
        OkHttpUtils
                .post()
                .tag("getYouHuiJuan")
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("id", id)
                .url(Api.getCoupon)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("领卷成功!");
                        adapter.mList.get(p).setIs_use("1");
                        adapter.commonAdapter.notifyDataSetChanged();
                    }
                });
    }

    public interface GetSuccessListener {
        void GetCodeSuccess();
    }

    private GetSuccessListener getSuccessListener;

    public void setGetSuccessListener(GetSuccessListener getSuccessListener) {
        this.getSuccessListener = getSuccessListener;
    }

    //取消订单
    public void cancelOrder(Activity activity, String order_id) {
        DialogUtil.show(activity);
        OkHttpUtils.getInstance().cancelTag("cancelOrder");
        OkHttpUtils
                .get()
                .tag("cancelOrder")
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("order_id", order_id)
                .url(Api.cancelOrder)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("取消成功!");
                        getSuccessListener.GetCodeSuccess();
                    }
                });
    }

    //删除订单
    public void delOrder(Activity activity, String order_id) {
        DialogUtil.show(activity);
        OkHttpUtils.getInstance().cancelTag("delOrder");
        OkHttpUtils
                .get()
                .tag("delOrder")
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("order_id", order_id)
                .url(Api.delOrder)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("删除成功!");
                        getSuccessListener.GetCodeSuccess();
                    }
                });
    }

    //确认收货
    public void confirmOrder(Activity activity, String order_id) {
        DialogUtil.show(activity);
        OkHttpUtils.getInstance().cancelTag("confirmOrder");
        OkHttpUtils
                .get()
                .tag("confirmOrder")
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .addParams("order_id", order_id)
                .url(Api.confirmOrder)
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        Ts.setText("收货成功!");
                        getSuccessListener.GetCodeSuccess();
                    }
                });
    }
}
