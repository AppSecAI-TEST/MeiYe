package com.duma.liudong.meiye.view.classift;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.Lg;
import com.duma.liudong.meiye.utils.StartUtil;
import com.duma.liudong.meiye.view.shoppingCart.fuwu.FuWuQueRenDinDanActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/31.
 */

public class ShangPingXiangQinWeb extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.web_webview)
    WebView webWebview;

    private String url;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_shangpinxiangqingweb);
    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
        Lg.e("跳转商品详情页url:   " + url);
        WebSettings mWebSettings = webWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        webWebview.addJavascriptInterface(new webJs(), "android");
        webWebview.loadUrl(url);

        webWebview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void OnBack() {
        back();
    }

    private void back() {
        if (webWebview.canGoBack()) {
            webWebview.goBack();
        } else {
            finish();
        }
    }

    @OnClick(R.id.img_back)
    public void onClick() {
        back();
    }

    class webJs {

        //返回userid 没有登录返回为空
        @JavascriptInterface
        public String getuserId() {
            return MyApplication.getSpUtils().getString(Constants.user_id);
        }

        //token 没有登录返回为空
        @JavascriptInterface
        public String gettoken() {
            return MyApplication.getSpUtils().getString(Constants.token);
        }

        //跳转登录
        @JavascriptInterface
        public void goLogin() {
            StartUtil.toLogin(mActivity);
        }

        //跳转店铺
        @JavascriptInterface
        public void goDianPu(String id) {
            StartUtil.toDianPu(mActivity, id);
        }

        //跳转TuanGou
        @JavascriptInterface
        public void goTuanGou(String goods_id) {
            Intent intent = new Intent(mActivity, FuWuQueRenDinDanActivity.class);
            intent.putExtra("goods_id", goods_id);
            startActivity(intent);
        }

        //跳转ShiWu
        @JavascriptInterface
        public void goShiwu(String goods_id) {
            StartUtil.toQueRenDinDan(mActivity, "goods_id", goods_id, "");
        }

        //跳转会员
        @JavascriptInterface
        public void goHuiYuan() {
            StartUtil.toH5Web(mActivity, Api.HuiYuanH5, "会员升级");
        }

        //跳转shiWuDinZhi  开团
        @JavascriptInterface
        public void goShiWuDinZhi(String goods_id) {
            StartUtil.toQueRenDinDan(mActivity, "goods_id", goods_id, "1");
        }

        @JavascriptInterface
        public void goCanTuan(String goods_id, String spell_id) {
            StartUtil.toQueRenDinDan(mActivity, "goods_id", goods_id, "", spell_id);
        }

        //跳转分享
        @JavascriptInterface
        public void goShare(String title, String url) {
            StartUtil.toShare(mActivity, title, url);
        }
    }
}
