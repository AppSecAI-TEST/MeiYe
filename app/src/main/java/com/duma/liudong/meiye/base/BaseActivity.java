package com.duma.liudong.meiye.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.ImageView;

import com.duma.liudong.meiye.R;

import butterknife.ButterKnife;


/**
 * Created by Thinkpad on 2016/4/19.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public Activity mActivity;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        ActivityCollector.addActivity(this);
        initContentView(savedInstanceState);
        ButterKnife.bind(this);
        initData();
        loadData();
    }

    // 初始化UI，setContentView等
    protected abstract void initContentView(Bundle savedInstanceState);

    protected void initData() {
    }

    protected void loadData() {
    }

    protected void OnBack() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            OnBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    //操作密码框的显示密码
    public void showPassword(boolean showOrHide, EditText editText, ImageView imageView) {
        if (showOrHide) {
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editText.setSelection(editText.getText().length());
            imageView.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_128));
        } else {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setSelection(editText.getText().length());
            imageView.setImageDrawable(MyApplication.getInstance().getResources().getDrawable(R.drawable.img_126));
        }
    }
}
