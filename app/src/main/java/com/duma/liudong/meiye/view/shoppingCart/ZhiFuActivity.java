package com.duma.liudong.meiye.view.shoppingCart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.base.MyStringCallback;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.DialogUtil;
import com.duma.liudong.meiye.utils.Lg;
import com.duma.liudong.meiye.utils.PayResult;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.utils.Ts;
import com.duma.liudong.meiye.view.dialog.QueRenUtilDialog;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 79953 on 2016/10/26.
 */

public class ZhiFuActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView layoutName;
    @BindView(R.id.layout_weixin)
    LinearLayout layoutWeixin;
    @BindView(R.id.layout_zhifubao)
    LinearLayout layoutZhifubao;
    @BindView(R.id.but_tijiao)
    Button butTijiao;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.rdoBtn_weixin)
    RadioButton rdoBtnWeixin;
    @BindView(R.id.rdoBtn_zhifubao)
    RadioButton rdoBtnZhifubao;
    @BindView(R.id.rdoBtn_yinlian)
    RadioButton rdoBtnYinlian;
    @BindView(R.id.layout_yinlian)
    LinearLayout layoutYinlian;
    private String id, money, type;//订单id 和 支付金额 和 订单类型

    private QueRenUtilDialog dialog;
    private IWXAPI wxapi;

    private String ClickType = "0";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_zhifu);
    }

    @Override
    protected void initData() {
        wxapi = WXAPIFactory.createWXAPI(this, Constants.Weixin);
        wxapi.registerApp(Constants.Weixin);
        layoutName.setText("收银台");
        dialog = new QueRenUtilDialog(this, "", "确认离开收银台", "继续支付", "确认离开");
        dialog.setYesClicklistener(new QueRenUtilDialog.OnYesClickListener() {
            @Override
            public void onYes() {
                StartUtil.toQuanBuDinDan(mActivity, type);
            }
        });
        id = getIntent().getStringExtra("id");
        money = getIntent().getStringExtra("money");
        type = getIntent().getStringExtra("type");
        tvMoney.setText(money + "元");

    }


    @OnClick({R.id.layout_back, R.id.but_tijiao, R.id.layout_weixin, R.id.layout_zhifubao, R.id.layout_yinlian, R.id.rdoBtn_weixin, R.id.rdoBtn_zhifubao, R.id.rdoBtn_yinlian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                dialog.show();
                break;
            case R.id.but_tijiao:
                if (rdoBtnWeixin.isChecked()) {
                    // LD: 微信支付
                    getWeiXinHttp();
                } else if (rdoBtnZhifubao.isChecked()) {
                    // LD: 支付包
                    getDinDanHttp();
                } else {
                    //银联
                    getYinLianHttp();
                }
                break;
            case R.id.layout_weixin:
                ClickType = "0";
                isCheck();
                break;
            case R.id.layout_zhifubao:
                ClickType = "1";
                isCheck();
                break;
            case R.id.rdoBtn_weixin:
                ClickType = "0";
                isCheck();
                break;
            case R.id.rdoBtn_zhifubao:
                ClickType = "1";
                isCheck();
                break;
            case R.id.rdoBtn_yinlian:
                ClickType = "2";
                isCheck();
                break;
            case R.id.layout_yinlian:
                ClickType = "2";
                isCheck();
                break;
        }
    }

    private void getYinLianHttp() {
//        DialogUtil.show(this);
//        OkHttpUtils
//                .get()
//                .tag(this)
//                .url(Api.yinlian)
//                .addParams("order_id", id)
//                .build()
//                .execute(new MyStringCallback() {
//                    @Override
//                    public void onMySuccess(String result) {
//                        DialogUtil.hide();
////                        YinLianBean bean = new Gson().fromJson(result, YinLianBean.class);
//                        String serverMode = "00";
//                        int i = UPPayAssistEx.startPay(ZhiFuActivity.this, null, null, result, serverMode);
//                        switch (i) {
//                            case UPPayAssistEx.PLUGIN_VALID:
//                                break;
//                            case UPPayAssistEx.PLUGIN_NOT_FOUND:
//                                Ts.setText("请安装银联支付控件!");
//                                break;
//                        }
//                    }
//
//                    @Override
//                    public void onError(String result) {
//                        DialogUtil.hide();
//                    }
//                });

    }

    public void isCheck() {
        switch (ClickType) {
            case "0":
                //微信
                rdoBtnWeixin.setChecked(true);
                rdoBtnZhifubao.setChecked(false);
                rdoBtnYinlian.setChecked(false);
                break;
            case "1":
                //支付宝
                rdoBtnWeixin.setChecked(false);
                rdoBtnZhifubao.setChecked(true);
                rdoBtnYinlian.setChecked(false);
                break;
            case "2":
                //银联
                rdoBtnWeixin.setChecked(false);
                rdoBtnZhifubao.setChecked(false);
                rdoBtnYinlian.setChecked(true);
                break;
        }

    }

    private static final int SDK_PAY_FLAG = 1;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    Lg.e("resultInfo ", resultInfo);
                    Lg.e("resultStatus ", resultStatus);
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        ZhiFuSuccess();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Ts.setText("支付结果确认中！");

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            ZhiFuShiBai();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    public void ZhiFuShiBai() {
        Ts.setText("支付失败");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        switch (intent.getStringExtra("type")) {
            case "0":
                ZhiFuSuccess();
                break;
            default:
                ZhiFuShiBai();
                break;
        }
    }

    private void ZhiFuSuccess() {
        Ts.setText("支付成功！");
        StartUtil.toZhiFuSuccess(mActivity, type);
        finish();
//        isTiaoZhuan();
    }

//    public void isTiaoZhuan() {
//        if (getIntent().getStringExtra("dinyi") == null || getIntent().getStringExtra("dinyi").equals("")) {
//            startActivity(new Intent(mActivity, FuKuanChenGongActivity.class));
//            finish();
//        } else {
//            Intent intent = new Intent();
//            intent.putExtra("order_sn", getIntent().getStringExtra("order_sn"));
//            intent.putExtra("money", getIntent().getStringExtra("money"));
//            intent.putExtra("id", id);
//            intent.setClass(mActivity, YiyuanChengGongActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }

    //微信
    private void getWeiXinHttp() {
//
//        DialogUtil.show(this, false);
//        OkHttpUtils
//                .get()
//                .tag(this)
//                .url(Api.huoqudindanwx)
//                .addParams("order_id", id)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        DialogUtil.hide();
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        DialogUtil.hide();
//                        WeiXinBean weiXinBean = new Gson().fromJson(response, WeiXinBean.class);
//                        PayReq req = new PayReq();
//                        req.appId = weiXinBean.getAppid();
//                        req.partnerId = weiXinBean.getPartnerid();
//                        req.prepayId = weiXinBean.getPrepayid();
//                        req.nonceStr = weiXinBean.getNoncestr();
//                        req.timeStamp = weiXinBean.getTimestamp();
//                        req.packageValue = "Sign=WXPay";
//                        req.sign = weiXinBean.getPaySign();
////                        req.extData = "app data"; // optional
//                        wxapi.sendReq(req);
//                    }
//                });


    }

    //支付宝
    private void getDinDanHttp() {
        DialogUtil.show(this, false);
        OkHttpUtils
                .get()
                .tag(this)
                .url(Api.PayserverOrder)
                .addParams("order_id", id)
                .addParams("user_id", MyApplication.getSpUtils().getString(Constants.user_id))
                .addParams("token", MyApplication.getSpUtils().getString(Constants.token))
                .build()
                .execute(new MyStringCallback() {
                    @Override
                    public void onMySuccess(String result) {
                        DialogUtil.hide();
                        String code = "";
                        try {
                            code = new JSONObject(result).getString("code");
                        } catch (JSONException e) {
                            Ts.JsonErroy();
                        }
                        goZhiFuBao(code);
                    }

                    @Override
                    public void onError(String result) {
                        DialogUtil.hide();
                    }
                });


    }

    private void goZhiFuBao(final String payInfo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(mActivity);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                Lg.e(result);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    //银联支付结果返回
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }

        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            ZhiFuSuccess();
        } else if (str.equalsIgnoreCase("fail")) {
            ZhiFuShiBai();
        } else if (str.equalsIgnoreCase("cancel")) {
            Lg.e("用户取消了支付");
        }
    }

    @Override
    protected void OnBack() {
        dialog.show();
    }

}
