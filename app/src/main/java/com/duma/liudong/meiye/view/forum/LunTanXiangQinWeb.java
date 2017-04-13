package com.duma.liudong.meiye.view.forum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseActivity;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudong on 17/3/31.
 */

public class LunTanXiangQinWeb extends BaseActivity {
    @BindView(R.id.web_webview)
    WebView webWebview;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_other)
    ImageView imgOther;
    @BindView(R.id.layout_other)
    LinearLayout layoutOther;

    private String url;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_luntan_web);
    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
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
        if (webWebview.canGoBack()) {
            webWebview.goBack();
        } else {
            finish();
        }
    }

    @OnClick(R.id.layout_back)
    public void onClick() {
        finish();
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

    }
}
