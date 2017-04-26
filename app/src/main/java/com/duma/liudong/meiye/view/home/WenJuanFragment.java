package com.duma.liudong.meiye.view.home;

import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.duma.liudong.meiye.R;
import com.duma.liudong.meiye.base.BaseFragment;
import com.duma.liudong.meiye.base.MyApplication;
import com.duma.liudong.meiye.utils.Api;
import com.duma.liudong.meiye.utils.Constants;
import com.duma.liudong.meiye.utils.StartUtil;

import butterknife.BindView;

/**
 * Created by liudong on 17/4/5.
 */

public class WenJuanFragment extends BaseFragment {
    @BindView(R.id.web_webview)
    WebView webWebview;

    @Override
    protected int setLayoutResouceId() {
        return R.layout.fragment_wenjuan;
    }


    @Override
    protected void initData() {
        WebSettings mWebSettings = webWebview.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        webWebview.addJavascriptInterface(new webJs(), "android");
        webWebview.loadUrl(Api.WenJuanH5);

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

        //跳转登录
        @JavascriptInterface
        public void goDianPu(String id) {
            StartUtil.toDianPu(mActivity, id);
        }

    }
}
